package cn.ycl.system.service.impl;

import cn.ycl.system.domain.SysConfig;
import cn.ycl.system.service.ISysConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参数配置 服务层实现
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {
    @Override
    public SysConfig selectConfigById(Long configId) {
        return null;
    }

    @Override
    public String selectConfigByKey(String configKey) {
        return null;
    }

    @Override
    public boolean selectCaptchaOnOff() {
        return false;
    }

    @Override
    public List<SysConfig> selectConfigList(SysConfig config) {
        return null;
    }

    @Override
    public int insertConfig(SysConfig config) {
        return 0;
    }

    @Override
    public int updateConfig(SysConfig config) {
        return 0;
    }

    @Override
    public void deleteConfigByIds(Long[] configIds) {

    }

    @Override
    public void loadingConfigCache() {

    }

    @Override
    public void clearConfigCache() {

    }

    @Override
    public void resetConfigCache() {

    }

    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        return null;
    }
}
