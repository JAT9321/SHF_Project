package com.jiao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jiao.base.BaseDao;
import com.jiao.base.BaseServiceImpl;
import com.jiao.dao.DictDao;
import com.jiao.entity.Dict;
import com.jiao.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional
@Service(interfaceClass = DictService.class)
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    protected BaseDao<Dict> getEntityDao() {
        return dictDao;
    }

    @Override
    public List<Map<String, Object>> findZnodes(Long id) {
        List<Dict> dictList = dictDao.findListByParentId(id);
        List<Map<String, Object>> zNodes = new ArrayList<>();
        for (Dict dict : dictList) {
            Integer count = dictDao.countIsParent(dict.getId());
            Map<String, Object> zNode = new HashMap<>();
            zNode.put("id", dict.getId());
            zNode.put("isParent", count > 0);
            zNode.put("name", dict.getName());
            zNodes.add(zNode);
        }
        return zNodes;
    }

    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        Dict dict = dictDao.getByDictCode(dictCode);
        if (null == dict) return null;
        return this.dictDao.findListByParentId(dict.getId());
    }

    @Override
    public List<Dict> findListByParentId(Long parentId) {
        return dictDao.findListByParentId(parentId);
    }
}
