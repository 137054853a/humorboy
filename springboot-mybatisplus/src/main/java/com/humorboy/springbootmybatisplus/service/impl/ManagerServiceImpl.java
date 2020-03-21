package com.humorboy.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humorboy.springbootmybatisplus.mapper.ManagerMapper;
import com.humorboy.springbootmybatisplus.service.IManagerService;
import com.humorboy.springbootmybatisplus.vo.Manager;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-03-12
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {

}
