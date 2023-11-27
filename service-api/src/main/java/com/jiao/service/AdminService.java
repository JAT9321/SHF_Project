package com.jiao.service;

import com.jiao.base.BaseService;
import com.jiao.entity.Admin;

import java.util.List;

public interface AdminService extends BaseService<Admin> {

    List<Admin> findAll();

    Admin getByUsername(String username);
}
