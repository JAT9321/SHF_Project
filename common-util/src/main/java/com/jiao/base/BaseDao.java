package com.jiao.base;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.Map;

public interface BaseDao<T> {

    Integer insert(T t);

    T findById(Serializable id);

    Integer update(T t);

    Integer updateToDeleteById(Serializable id);

    Page<T> findPage(Map<String, Object> filters);

}
