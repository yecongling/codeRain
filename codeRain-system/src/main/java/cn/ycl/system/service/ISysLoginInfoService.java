package cn.ycl.system.service;


import cn.ycl.system.domain.SysLoginInfo;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层
 * 
 * @author ruoyi
 */
public interface ISysLoginInfoService {
    /**
     * 新增系统登录日志
     * 
     * @param loginInfo 访问日志对象
     */
    void insertLoginInfo(SysLoginInfo loginInfo) throws Exception;

    /**
     * 查询系统登录日志集合
     * 
     * @param loginInfo 访问日志对象
     * @return 登录记录集合
     */
    List<SysLoginInfo> selectLoginInfoList(SysLoginInfo loginInfo) throws Exception;

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    int deleteLoginInfoByIds(String ids) throws Exception;

    /**
     * 清空系统登录日志
     */
    void cleanLoginInfo() throws Exception;
}
