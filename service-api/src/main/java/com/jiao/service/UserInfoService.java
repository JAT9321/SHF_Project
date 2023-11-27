package com.jiao.service;

import com.jiao.base.BaseService;
import com.jiao.entity.UserInfo;

public interface UserInfoService extends BaseService<UserInfo> {
    UserInfo getByPhone(String phone);
}