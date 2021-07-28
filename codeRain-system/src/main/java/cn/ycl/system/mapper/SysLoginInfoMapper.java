package cn.ycl.system.mapper;


import cn.ycl.system.domain.SysLoginInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author ruoyi
 */
public interface SysLoginInfoMapper {
    /**
     * 新增系统登录日志
     * 
     * @param loginInfo 访问日志对象
     */
    void insertLoginInfo(SysLoginInfo loginInfo) throws SQLException;

    /**
     * 查询系统登录日志集合
     * 
     * @param loginInfo 访问日志对象
     * @return 登录记录集合
     */
    List<SysLoginInfo> selectLoginInfoList(SysLoginInfo loginInfo) throws SQLException;

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteLoginInfoByIds(String[] ids) throws SQLException;

    /**
     * 清空系统登录日志
     * 
     * @return 结果
     */
    int cleanLoginInfo() throws SQLException;
}
