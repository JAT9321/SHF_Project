package com.jiao.base;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiao.util.CastUtil;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Map;


public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected abstract BaseDao<T> getEntityDao();

    public Integer save(T t) {
        return getEntityDao().insert(t);
    }

    public T getById(Serializable id) {
        return getEntityDao().findById(id);
    }

    public Integer update(T t) {
        return getEntityDao().update(t);
    }

    public Integer deleteById(Serializable id) {
        return getEntityDao().updateToDeleteById(id);
    }

    public PageInfo<T> findPage(Map<String, Object> filters) {
        // 当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页数量
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 5);
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<T>(getEntityDao().findPage(filters), 10);
    }
}
