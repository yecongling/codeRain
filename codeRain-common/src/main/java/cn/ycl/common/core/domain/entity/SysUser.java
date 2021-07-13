package cn.ycl.common.core.domain.entity;

import cn.ycl.common.core.domain.BaseEntity;

/**
 * 用户对象
 */
public class SysUser extends BaseEntity {

    private String username;
    private String password;

    private Long userId;

    private String loginName;

    public SysUser() {
    }

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
