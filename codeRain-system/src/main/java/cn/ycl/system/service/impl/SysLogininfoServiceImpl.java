package cn.ycl.system.service.impl;

import cn.ycl.system.domain.SysLogininfo;
import cn.ycl.system.mapper.SysLogininfoMapper;
import cn.ycl.system.service.ISysLogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysLogininfoServiceImpl implements ISysLogininfoService {


    private SysLogininfoMapper logininfoMapper;

    @Autowired
    public void setLogininfoMapper(SysLogininfoMapper logininfoMapper) {
        this.logininfoMapper = logininfoMapper;
    }

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfo(SysLogininfo logininfor) {
        logininfoMapper.insertLogininfor(logininfor);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfo> selectLogininforList(SysLogininfo logininfor) {
        return null;
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLogininforByIds(String ids) {
        return 0;
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor() {

    }
}
