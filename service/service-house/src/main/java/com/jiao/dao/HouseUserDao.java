package com.jiao.dao;

import com.jiao.base.BaseDao;
import com.jiao.entity.HouseUser;

import java.util.List;

public interface HouseUserDao extends BaseDao<HouseUser> {

    List<HouseUser> findListByHouseId(Long houseId);
}