package cn.ycl.common.exception.user;

import cn.ycl.common.exception.base.BaseException;

/**
 * 用户信息异常类
 */
public class UserException extends BaseException {

    private static final long serialVersionUID = -8220980046315862306L;

    public UserException(String code, Object[] args){
        super("user", code, args, null);
    }
}
