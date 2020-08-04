package com.practise.springbootes.repository;

import com.practise.springbootes.vo.UserInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserInfoRepository extends ElasticsearchRepository<UserInfo, String> {

    UserInfo findByName(String name);
    List<UserInfo> findByFromArea(String area);
}