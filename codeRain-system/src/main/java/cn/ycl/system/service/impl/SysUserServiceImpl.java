package cn.ycl.system.service.impl;

import cn.ycl.common.constant.UserConstants;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.system.mapper.SysUserMapper;
import cn.ycl.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);


    private SysUserMapper userMapper;

    @Autowired
    public void setUserMapper(SysUserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return null;
    }

    @Override
    public List<SysUser> selectAllocatedList(SysUser user) {
        return null;
    }

    @Override
    public List<SysUser> selectUnallocatedList(SysUser user) {
        return null;
    }

    @Override
    public SysUser selectUserByLoginName(String userName) {
        return userMapper.selectUserByLoginName(userName);
    }

    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public SysUser selectUserByEmail(String email) {
        return null;
    }

    @Override
    public SysUser selectUserById(Long userId) {
        return null;
    }

    @Override
    public int deleteUserById(Long userId) {
        return 0;
    }

    @Override
    public int deleteUserByIds(String ids) {
        return 0;
    }

    @Override
    public int insertUser(SysUser user) {
        return 0;
    }

    /**
     * 注册用户信息
     * @param user 用户信息
     * @return
     */
    @Override
    public boolean registerUser(SysUser user) {
        user.setUserType(UserConstants.REGISTER_USER_TYPE);
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public int updateUser(SysUser user) {
        return 0;
    }

    @Override
    public int updateUserInfo(SysUser user) {
        return 0;
    }

    @Override
    public void insertUserAuth(Long userId, Long[] roleIds) {

    }

    @Override
    public int resetUserPwd(SysUser user) {
        return 0;
    }

    @Override
    public String checkLoginNameUnique(String loginName) {
        return null;
    }

    @Override
    public String checkPhoneUnique(SysUser user) {
        return null;
    }

    @Override
    public String checkEmailUnique(SysUser user) {
        return null;
    }

    @Override
    public void checkUserAllowed(SysUser user) {

    }

    @Override
    public String selectUserRoleGroup(Long userId) {
        return null;
    }

    @Override
    public String selectUserPostGroup(Long userId) {
        return null;
    }
}
