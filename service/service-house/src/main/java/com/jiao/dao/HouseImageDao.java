package com.jiao.dao;

import com.jiao.base.BaseDao;
import com.jiao.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseImageDao extends BaseDao<HouseImage> {

    List<HouseImage> findList(@Param("houseId") Long houseId, @Param("type") Integer type);
}