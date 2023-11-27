package com.jiao.service;

import com.jiao.base.BaseService;
import com.jiao.entity.HouseBroker;

import java.util.List;

public interface HouseBrokerService extends BaseService<HouseBroker> {

    List<HouseBroker> findListByHouseId(Long houseId);
}