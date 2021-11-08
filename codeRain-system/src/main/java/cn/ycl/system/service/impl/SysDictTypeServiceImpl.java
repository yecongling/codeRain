package cn.ycl.system.service.impl;

import cn.ycl.common.core.domain.entity.SysDictData;
import cn.ycl.common.core.domain.entity.SysDictType;
import cn.ycl.common.utils.DictUtils;
import cn.ycl.common.utils.StringUtils;
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

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    @Override
    public List<SysDictType> selectDictTypeAll() {
        return null;
    }

    /**
     * 根据字典类型查询字典数据
     * @param dictType 字典类型
     * @return
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        List<SysDictData> dictData = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(dictData)){
            return dictData;
        }
        dictData = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictData)){
            DictUtils.setDictCache(dictType, dictData);
            return dictData;
        }
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
