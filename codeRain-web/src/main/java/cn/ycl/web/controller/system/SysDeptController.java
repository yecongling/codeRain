package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.entity.SysDept;
import cn.ycl.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门信息controller
 */
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {

    private ISysDeptService deptService;
    @Autowired
    public void setDeptService(ISysDeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 获取部门们列表
     * @param dept 部门信息（筛选条件）
     * @return
     */
    @GetMapping("/list")
    public AjaxResult list(SysDept dept){
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(depts);
    }

    /**
     * 获取部门下拉树列表
     * @param dept 部门信息
     * @return 部门下拉树列表
     */
    @GetMapping("/treeSelect")
    public AjaxResult treeSelect(SysDept dept){
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(deptService.buildDeptTreeSelect(depts));
    }

    /**
     * 加载对应角色的部门列表树
     * @param roleId 角色ID
     * @return 返回角色对应的部门列表树
     */
    @GetMapping(value = "/roleDeptTreeSelect/{roleId}")
    public AjaxResult roleDeptTreeSelect(@PathVariable("roleId") Long roleId) {
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        ajax.put("depts", deptService.buildDeptTreeSelect(depts));
        return ajax;
    }
}
