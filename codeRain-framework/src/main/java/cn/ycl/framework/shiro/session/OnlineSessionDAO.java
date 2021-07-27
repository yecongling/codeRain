package cn.ycl.framework.shiro.session;

import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Value;

/**
 * 针对自定义的ShiroSession的db操作
 */
public class OnlineSessionDAO extends EnterpriseCacheSessionDAO {
    /**
     * 同步session到数据库的周期 单位为毫秒（默认1分钟）
     */
    @Value("${shiro.session.dbSyncPeriod}")
    private int dbSyncPeriod;

    /**
     * 上次同步数据库的时间戳
     */
    private static final String LAST_SYNC_DB_TIMESTAMP = OnlineSessionDAO.class.getName() + "LAST_SYNC_DB_TIMESTAMP";

    public OnlineSessionDAO(){
        super();
    }

    public OnlineSessionDAO(long expireTime){
        super();
    }

}
