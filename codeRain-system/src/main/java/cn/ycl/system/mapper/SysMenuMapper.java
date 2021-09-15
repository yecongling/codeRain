package cn.ycl.system.mapper;

import cn.ycl.system.domain.SysMenu;
import org.apache.ibatis.annotations.Param;

/**
 * 菜单表数据层
 */
public interface SysMenuMapper {

    /**
     * 校验菜单名称是否唯一
     *
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    public SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);
}
