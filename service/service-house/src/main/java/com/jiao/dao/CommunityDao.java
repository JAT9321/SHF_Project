package com.jiao.dao;

import com.jiao.base.BaseDao;
import com.jiao.entity.Community;

import java.util.List;

public interface CommunityDao extends BaseDao<Community> {
    List<Community> findAll();

    Community getById(Long id);
}