package com.jiao.dao;

import com.jiao.base.BaseDao;
import com.jiao.entity.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo> {
    UserInfo getByPhone(String phone);
}
