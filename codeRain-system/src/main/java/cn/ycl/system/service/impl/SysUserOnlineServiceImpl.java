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
    public SysUserOnline selectOnlineById(String sessionId) throws Exception {
        return null;
    }

    @Override
    public void deleteOnlineById(String sessionId) throws Exception {

    }

    @Override
    public void batchDeleteOnline(List<String> sessions) throws Exception {

    }

    @Override
    public void saveOnline(SysUserOnline online) throws Exception {

    }

    @Override
    public List<SysUserOnline> selectUserOnlineList(SysUserOnline userOnline) throws Exception {
        return null;
    }

    @Override
    public void forceLogout(String sessionId) throws Exception {

    }

    @Override
    public void removeUserCache(String loginName, String sessionId) throws Exception {

    }

    @Override
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) throws Exception {
        return null;
    }
}
