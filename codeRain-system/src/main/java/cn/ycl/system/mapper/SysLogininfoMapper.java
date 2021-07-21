package cn.ycl.system.mapper;


import cn.ycl.system.domain.SysLogininfo;

import java.util.List;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author ruoyi
 */
public interface SysLogininfoMapper {
    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfo logininfor);

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<SysLogininfo> selectLogininfoList(SysLogininfo logininfor);

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteLogininfoByIds(String[] ids);

    /**
     * 清空系统登录日志
     * 
     * @return 结果
     */
    int cleanLogininfo();
}
