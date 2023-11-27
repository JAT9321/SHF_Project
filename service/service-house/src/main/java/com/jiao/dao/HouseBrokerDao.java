package com.jiao.dao;

import com.jiao.base.BaseDao;
import com.jiao.entity.HouseBroker;

import java.util.List;

public interface HouseBrokerDao extends BaseDao<HouseBroker> {

    List<HouseBroker> findListByHouseId(Long houseId);
}