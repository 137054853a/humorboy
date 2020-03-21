package com.humorboy.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humorboy.springbootmybatisplus.mapper.ProjectManagerMapper;
import com.humorboy.springbootmybatisplus.service.IProjectManagerService;
import com.humorboy.springbootmybatisplus.vo.ProjectManager;
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
public class ProjectManagerServiceImpl extends ServiceImpl<ProjectManagerMapper, ProjectManager> implements IProjectManagerService {

}
