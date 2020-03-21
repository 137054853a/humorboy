package com.humorboy.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humorboy.springbootmybatisplus.service.ICostApplicationService;
import com.humorboy.springbootmybatisplus.mapper.CostApplicationMapper;
import com.humorboy.springbootmybatisplus.vo.CostApplication;
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
public class CostApplicationServiceImpl extends ServiceImpl<CostApplicationMapper, CostApplication> implements ICostApplicationService {

}
