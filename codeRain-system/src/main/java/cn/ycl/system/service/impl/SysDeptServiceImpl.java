package cn.ycl.system.service.impl;

import cn.ycl.common.core.domain.TreeSelect;
import cn.ycl.common.core.domain.entity.SysDept;
import cn.ycl.system.mapper.SysDeptMapper;
import cn.ycl.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门管理 服务实现类
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService {
    private SysDeptMapper deptMapper;

    @Autowired
    public void setDeptMapper(SysDeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    /**
     * 查询部门管理数据
     * @param dept 部门信息（筛选条件）
     * @return
     */
    @Override
    public List<SysDept> selectDeptList(SysDept dept) {
        return deptMapper.selectDeptList(dept);
    }

    @Override
    public List<SysDept> buildDeptTree(List<SysDept> depts) {
        return null;
    }

    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts) {
        return null;
    }

    @Override
    public List<Integer> selectDeptListByRoleId(Long roleId) {
        return null;
    }

    @Override
    public SysDept selectDeptById(Long deptId) {
        return null;
    }

    @Override
    public int selectNormalChildrenDeptById(Long deptId) {
        return 0;
    }

    @Override
    public boolean hasChildByDeptId(Long deptId) {
        return false;
    }

    @Override
    public boolean checkDeptExistUser(Long deptId) {
        return false;
    }

    @Override
    public String checkDeptNameUnique(SysDept dept) {
        return null;
    }

    @Override
    public void checkDeptDataScope(Long deptId) {

    }

    @Override
    public int insertDept(SysDept dept) {
        return 0;
    }

    @Override
    public int updateDept(SysDept dept) {
        return 0;
    }

    @Override
    public int deleteDeptById(Long deptId) {
        return 0;
    }
}
