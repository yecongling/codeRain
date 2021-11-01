package cn.ycl.common.utils;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.core.domain.entity.SysDictData;
import cn.ycl.common.core.redis.RedisCache;
import cn.ycl.common.utils.spring.SpringUtils;

import java.util.List;

/**
 * 字典工具类
 */
public class DictUtils {
    /**
     * 分隔符
     */
    public static final String SEPARATOR = ",";

    /**
     * 设置字典缓存
     * @param key 缓存key
     * @param dictData 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> dictData){
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), dictData);
    }

    /**
     * 获取字典缓存
     * @param key 缓存key
     * @return 字典数据列表
     */
    public static List<SysDictData> getDictCache(String key){
        Object object = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(object)){
            return StringUtils.cast(object);
        }
        return null;
    }
    /**
     * 设置 cache key
     * @param configKey 参数键
     * @return 缓存key
     */
    public static String getCacheKey(String configKey){
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}
