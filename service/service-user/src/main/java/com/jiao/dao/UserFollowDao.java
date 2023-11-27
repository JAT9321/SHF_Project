package com.jiao.dao;

import com.github.pagehelper.Page;
import com.jiao.base.BaseDao;
import com.jiao.entity.UserFollow;
import com.jiao.vo.UserFollowVo;
import org.apache.ibatis.annotations.Param;

public interface UserFollowDao extends BaseDao<UserFollow> {
    Integer countByUserIdAndHouserId(@Param("userId") Long userId, @Param("houseId") Long houseId);

    Page<UserFollowVo> findListPage(@Param("userId") Long userId);
}