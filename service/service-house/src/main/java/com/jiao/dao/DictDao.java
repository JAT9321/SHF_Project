package com.jiao.dao;

import com.jiao.base.BaseDao;
import com.jiao.entity.Dict;

import java.util.List;

public interface DictDao extends BaseDao<Dict> {


    List<Dict> findListByParentId(Long parentId);

    Integer countIsParent(Long id);

    String getNameById(Long id);

    Dict getByDictCode(String dictCode);

}
