package cn.ycl.system.service.impl;

import cn.ycl.system.domain.SysUserOnline;
import cn.ycl.system.service.ISysUserOnlineService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 在线用户 服务层处理
 */
@Service
public class SysUserOnlineServiceImpl implements ISysUserOnlineService {
    @Override
    public SysUserOnline selectOnlineById(String sessionId) {
        return null;
    }

    @Override
    public void deleteOnlineById(String sessionId) {

    }

    @Override
    public void batchDeleteOnline(List<String> sessions) {

    }

    @Override
    public void saveOnline(SysUserOnline online) {

    }

    @Override
    public List<SysUserOnline> selectUserOnlineList(SysUserOnline userOnline) {
        return null;
    }

    @Override
    public void forceLogout(String sessionId) {

    }

    @Override
    public void removeUserCache(String loginName, String sessionId) {

    }

    @Override
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        return null;
    }
}
