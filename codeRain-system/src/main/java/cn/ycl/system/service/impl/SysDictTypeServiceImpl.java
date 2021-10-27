package cn.ycl.system.service.impl;

import cn.ycl.common.core.domain.entity.SysDictData;
import cn.ycl.common.core.domain.entity.SysDictType;
import cn.ycl.system.mapper.SysDictDataMapper;
import cn.ycl.system.mapper.SysDictTypeMapper;
import cn.ycl.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典类型业务层处理
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    private SysDictTypeMapper dictTypeMapper;
    @Autowired
    public void setDictTypeMapper(SysDictTypeMapper dictTypeMapper) {
        this.dictTypeMapper = dictTypeMapper;
    }

    private SysDictDataMapper dictDataMapper;
    @Autowired
    public void setDictDataMapper(SysDictDataMapper dictDataMapper) {
        this.dictDataMapper = dictDataMapper;
    }

    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return null;
    }

    @Override
    public List<SysDictType> selectDictTypeAll() {
        return null;
    }

    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {

        return null;
    }

    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return null;
    }

    @Override
    public SysDictType selectDictTypeByType(String dictType) {
        return null;
    }

    @Override
    public void deleteDictTypeByIds(Long[] dictIds) {

    }

    @Override
    public void loadingDictCache() {

    }

    @Override
    public void clearDictCache() {

    }

    @Override
    public void resetDictCache() {

    }

    @Override
    public int insertDictType(SysDictType dictType) {
        return 0;
    }

    @Override
    public int updateDictType(SysDictType dictType) {
        return 0;
    }

    @Override
    public String checkDictTypeUnique(SysDictType dictType) {
        return null;
    }
}
