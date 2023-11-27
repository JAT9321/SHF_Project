package com.jiao.dao;

import com.github.pagehelper.Page;
import com.jiao.base.BaseDao;
import com.jiao.entity.House;
import com.jiao.vo.HouseQueryVo;
import com.jiao.vo.HouseVo;
import org.apache.ibatis.annotations.Param;

public interface HouseDao extends BaseDao<House> {

    Page<HouseVo> findListPage(@Param("vo") HouseQueryVo houseQueryVo);

}
