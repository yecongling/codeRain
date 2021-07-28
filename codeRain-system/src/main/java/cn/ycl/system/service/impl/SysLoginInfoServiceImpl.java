package cn.ycl.system.service.impl;

import cn.ycl.system.domain.SysLoginInfo;
import cn.ycl.system.mapper.SysLoginInfoMapper;
import cn.ycl.system.service.ISysLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysLoginInfoServiceImpl implements ISysLoginInfoService {


    private SysLoginInfoMapper loginInfoMapper;

    @Autowired
    public void setLoginInfoMapper(SysLoginInfoMapper loginInfoMapper) {
        this.loginInfoMapper = loginInfoMapper;
    }

    /**
     * 新增系统登录日志
     *
     * @param loginInfo 访问日志对象
     */
    @Override
    public void insertLoginInfo(SysLoginInfo loginInfo) throws Exception{
        loginInfoMapper.insertLoginInfo(loginInfo);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param loginInfo 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLoginInfo> selectLoginInfoList(SysLoginInfo loginInfo)  throws Exception{
        return null;
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLoginInfoByIds(String ids) throws Exception {
        return 0;
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginInfo()  throws Exception{

    }
}
