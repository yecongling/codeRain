package cn.ycl.system.service.impl;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.core.redis.RedisCache;
import cn.ycl.common.core.text.Convert;
import cn.ycl.common.utils.StringUtils;
import cn.ycl.system.domain.SysConfig;
import cn.ycl.system.mapper.SysConfigMapper;
import cn.ycl.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参数配置 服务层实现
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {

    private SysConfigMapper configMapper;
    @Autowired
    public void setConfigMapper(SysConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    private RedisCache redisCache;
    @Autowired
    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Override
    public SysConfig selectConfigById(Long configId) {
        return null;
    }

    /**
     * 根据键名查询参数配置信息
     * @param configKey 参数键名
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
        if (StringUtils.isNotEmpty(configValue)){
            return configValue;
        }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig sysConfig = configMapper.selectConfig(config);
        if (StringUtils.isNotNull(sysConfig)){
            redisCache.setCacheObject(getCacheKey(configKey), sysConfig.getConfigValue());
            return sysConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public boolean selectCaptchaOnOff() {
        return false;
    }

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfig> selectConfigList(SysConfig config) {
        return configMapper.selectConfigList(config);
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

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey){
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}
