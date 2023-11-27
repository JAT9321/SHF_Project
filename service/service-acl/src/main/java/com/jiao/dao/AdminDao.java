package com.jiao.dao;

import com.jiao.base.BaseDao;
import com.jiao.entity.Admin;

import java.util.List;

public interface AdminDao extends BaseDao<Admin> {

    List<Admin> findAll();

    Admin getByUsername(String username);

}
