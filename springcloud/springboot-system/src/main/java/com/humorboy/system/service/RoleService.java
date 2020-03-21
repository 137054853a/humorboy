package com.humorboy.system.service;

import com.humorboy.system.repository.RoleRepository;
import com.humorboy.commons.vo.system.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAllById(List<Long> ids) {
        List<Role> lists = roleRepository.findAllById(ids);
        return lists;
    }

    //查询所有角色
    public Set<String> findAllNames() {
        List<Role> list = roleRepository.findAll();
        Set<String> roleNames = new HashSet<>();
        for (Role role : list) {
            String roleName = role.getRoleName();
            roleNames.add(roleName);
        }
        return roleNames;
    }


}
