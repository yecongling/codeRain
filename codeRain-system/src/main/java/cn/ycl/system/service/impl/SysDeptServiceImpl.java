package cn.ycl.system.service.impl;

import cn.ycl.common.core.domain.TreeSelect;
import cn.ycl.common.core.domain.entity.SysDept;
import cn.ycl.common.core.domain.entity.SysRole;
import cn.ycl.common.utils.StringUtils;
import cn.ycl.system.mapper.SysDeptMapper;
import cn.ycl.system.mapper.SysRoleMapper;
import cn.ycl.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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


    private SysRoleMapper roleMapper;
    @Autowired
    public void setRoleMapper(SysRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息（筛选条件）
     * @return
     */
    @Override
    public List<SysDept> selectDeptList(SysDept dept) {
        return deptMapper.selectDeptList(dept);
    }

    /**
     * 构建树结构数据
     *
     * @param depts 部门列表
     * @return 树结构数据
     */
    @Override
    public List<SysDept> buildDeptTree(List<SysDept> depts) {
        List<SysDept> returnList = new ArrayList<>();
        ArrayList<Long> tempList = new ArrayList<>();
        for (SysDept dept : depts) {
            tempList.add(dept.getDeptId());
        }
        for (SysDept dept : depts) {
            // 如果是顶级节点，遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId())) {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty()){
            returnList = depts;
        }
        return returnList;
    }

    /**
     * 构建前端需要的下拉树结构
     *
     * @param depts 部门列表
     * @return 树结构数据
     */
    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts) {
        List<SysDept> sysDepts = buildDeptTree(depts);
        return sysDepts.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Override
    public List<Integer> selectDeptListByRoleId(Long roleId) {
        SysRole role = roleMapper.selectRoleById(roleId);
        return deptMapper.selectDeptListByRoleId(roleId, role.isDeptCheckStrictly());
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

    /**
     * 递归列表
     */
    private void recursionFn(List<SysDept> list, SysDept t) {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDept tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
        List<SysDept> tlist = new ArrayList<>();
        Iterator<SysDept> it = list.iterator();
        while (it.hasNext()) {
            SysDept n = it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getDeptId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t) {
        return getChildList(list, t).size() > 0;
    }
}
