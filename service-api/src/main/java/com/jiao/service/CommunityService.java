package com.jiao.service;

import com.jiao.base.BaseService;
import com.jiao.entity.Community;

import java.util.List;

public interface CommunityService extends BaseService<Community> {

    List<Community> findAll();

    Community getById(Long id);
}
