package com.humorboy.system.configuation;

import com.humorboy.system.util.ReturnResult;
import com.humorboy.commons.vo.system.Permission;
import com.humorboy.commons.vo.system.Role;
import com.humorboy.commons.vo.system.User;
import com.humorboy.system.repository.UserRepository;
import com.humorboy.system.service.PermissionService;
import com.humorboy.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        Set<Role> roles = user.getRoles();
        Set<String> permissionNames = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //得到角色名
        Set<String> roleNames = roles.stream().map(x -> {
            return x.getRoleName();
        }).collect(Collectors.toSet());
        //根据角色获取所有资源信息
        Set<Permission> permissions = permissionService.selectPermis(roles);
        //获取所有资源名称
        for (Iterator<Permission> iterator = permissions.iterator(); iterator.hasNext(); ) {
            permissionNames.add(iterator.next().getPermisName());
        }

        info.setRoles(roleNames);
        info.setStringPermissions(permissionNames);

        return info;
    }

    /**
     * shiro登录认证 用户提交 用户名和密码 -- shiro 封装令牌 realm通过用户名将密码查询返回 ----shiro 自动去比较查询出密码和用户输入密码是否一致 --进行登录控制
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("Shiro登录认证开始");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userRepository.findByUserName(token.getUsername());
//         账号不存在
        if (user == null) {
            return null;
        }
        //账号无效
        if (0 == user.getStatus()) {
            return (AuthenticationInfo) ReturnResult.format("账号无效，请联系管理员！");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPwd(),
                getName());
        //当验证都通过后，把用户信息放在Redis里
        redisTemplate.opsForValue().set("user", user);
        return authenticationInfo;
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        User user = (User) principals.getPrimaryPrincipal();
        removeUserCache(user);
    }

    /**
     * 清除用户缓存
     *
     * @param user
     */
    public void removeUserCache(User user) {
        removeUserCache(user.getUserName());
    }

    /**
     * 清除用户缓存
     *
     * @param loginName
     */
    public void removeUserCache(String loginName) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(loginName, super.getName());
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        User user = (User) principals.getPrimaryPrincipal();
        Set<Role> roles = user.getRoles();

        for (Role role : roles) {
            String roleName = role.getRoleName();
            if (role.getRoleId().equals("00")) {
                return true || super.isPermitted(principals, permission);
            }
        }
        return false || super.isPermitted(principals, permission);
    }

    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        User user = (User) principals.getPrimaryPrincipal();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            String roleName = role.getRoleName();
            if (role.getRoleId().equals("00")) {
                return true || super.isPermitted(principals, roleIdentifier);
            }
        }
        return false || super.hasRole(principals, roleIdentifier);
    }


}
