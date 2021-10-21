package cn.ycl.system.service.impl;

import cn.ycl.system.domain.SysOperLog;
import cn.ycl.system.service.ISysOperLogService;

import java.util.List;

/**
 * 操作日志 服务层处理
 */
public class SysOperLogServiceImpl implements ISysOperLogService {
    @Override
    public void insertOperlog(SysOperLog operLog){

    }

    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) throws Exception {
        return null;
    }

    @Override
    public int deleteOperLogByIds(String ids) throws Exception {
        return 0;
    }

    @Override
    public SysOperLog selectOperLogById(Long operId) throws Exception {
        return null;
    }

    @Override
    public void cleanOperLog() throws Exception {

    }
}
