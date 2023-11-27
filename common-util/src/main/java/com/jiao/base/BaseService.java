package com.jiao.base;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.Map;

public interface BaseService <T>{

    Integer save(T t);

    T getById(Serializable id);

    Integer update(T t);

    Integer deleteById(Serializable id);

    PageInfo<T> findPage(Map<String, Object> filters);

}
