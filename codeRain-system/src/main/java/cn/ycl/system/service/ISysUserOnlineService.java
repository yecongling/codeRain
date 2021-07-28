package cn.ycl.system.service;


import cn.ycl.system.domain.SysUserOnline;

import java.util.Date;
import java.util.List;

/**
 * 在线用户 服务层
 *
 * @author ruoyi
 */
public interface ISysUserOnlineService {
    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    SysUserOnline selectOnlineById(String sessionId) throws Exception;

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    void deleteOnlineById(String sessionId) throws Exception;

    /**
     * 通过会话序号删除信息
     *
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    void batchDeleteOnline(List<String> sessions) throws Exception;

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    void saveOnline(SysUserOnline online) throws Exception;

    /**
     * 查询会话集合
     *
     * @param userOnline 分页参数
     * @return 会话集合
     */
    List<SysUserOnline> selectUserOnlineList(SysUserOnline userOnline) throws Exception;

    /**
     * 强退用户
     *
     * @param sessionId 会话ID
     */
    void forceLogout(String sessionId) throws Exception;

    /**
     * 清理用户缓存
     *
     * @param loginName 登录名称
     * @param sessionId 会话ID
     */
    void removeUserCache(String loginName, String sessionId) throws Exception;

    /**
     * 查询会话集合
     *
     * @param expiredDate 有效期
     * @return 会话集合
     */
    List<SysUserOnline> selectOnlineByExpired(Date expiredDate) throws Exception;
}
