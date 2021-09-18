package cn.ycl.system.service.impl;

import cn.ycl.common.constant.UserConstants;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.common.utils.StringUtils;
import cn.ycl.system.domain.SysMenu;
import cn.ycl.system.domain.ZTree;
import cn.ycl.system.mapper.SysMenuMapper;
import cn.ycl.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单业务层处理
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {


    public SysMenuMapper sysMenuMapper;

    @Autowired
    public void setSysMenuMapper(SysMenuMapper sysMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
    }

    @Override
    public List<SysMenu> selectMenusByUser(SysUser user) {
        return null;
    }

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
        List<SysMenu> menuList = null;
        if(SysUser.isAdmin(userId)){
            return sysMenuMapper.selectMenuList(menu);
        }

        return null;
    }

    @Override
    public List<SysMenu> selectMenuAll(Long userId) {
        List<SysMenu> menuList;
        if (SysUser.isAdmin(userId)){
            menuList = sysMenuMapper.selectMenuAll();
        } else {
            menuList = sysMenuMapper.selectMenuAllByUserId(userId);
        }
        return menuList;
    }

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        return null;
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<ZTree> initZTree(List<SysMenu> menuList) {
        return initZTree(menuList, null);
    }

    /**
     * 对象转菜单树
     * @param menuList 菜单集合
     * @param roleMenuList 角色已存在菜单列表
     * @return
     */
    public List<ZTree> initZTree(List<SysMenu> menuList, List<String> roleMenuList){
        ArrayList<ZTree> trees = new ArrayList<>();
        for (SysMenu sysMenu : menuList) {
            ZTree zTree = new ZTree();
            zTree.setId(sysMenu.getMenuId());
            zTree.setPId(sysMenu.getParentId());
            zTree.setName(sysMenu.getMenuName());
            zTree.setTitle(sysMenu.getMenuName());
            trees.add(zTree);
        }
        return trees;
    }

    @Override
    public List<ZTree> menuTreeData(Long userId) {
        List<SysMenu> menuList = selectMenuAll(userId);
        return initZTree(menuList);
    }

    @Override
    public Map<String, String> selectPermsAll(Long userId) {
        return null;
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    @Override
    public SysMenu selectMenuById(Long menuId) {
        return null;
    }

    @Override
    public int selectCountMenuByParentId(Long parentId) {
        return 0;
    }

    @Override
    public int selectCountRoleMenuByMenuId(Long menuId) {
        return 0;
    }

    @Override
    public int insertMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenu sysMenu = sysMenuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(sysMenu) && sysMenu.getMenuId().longValue() != menuId.longValue()){
            return UserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }
}
