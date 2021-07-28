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
    public List<SysUser> selectUserList(SysUser user) throws Exception{
        return null;
    }

    @Override
    public List<SysUser> selectAllocatedList(SysUser user) throws Exception{
        return null;
    }

    @Override
    public List<SysUser> selectUnallocatedList(SysUser user) throws Exception{
        return null;
    }

    @Override
    public SysUser selectUserByLoginName(String userName) throws Exception{
        return userMapper.selectUserByLoginName(userName);
    }

    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber) throws Exception {
        return null;
    }

    @Override
    public SysUser selectUserByEmail(String email) throws Exception {
        return null;
    }

    @Override
    public SysUser selectUserById(Long userId) throws Exception {
        return null;
    }

    @Override
    public int deleteUserById(Long userId) throws Exception {
        return 0;
    }

    @Override
    public int deleteUserByIds(String ids) throws Exception {
        return 0;
    }

    @Override
    public int insertUser(SysUser user) throws Exception {
        return 0;
    }

    /**
     * 注册用户信息
     * @param user 用户信息
     * @return
     */
    @Override
    public boolean registerUser(SysUser user) throws Exception {
        user.setUserType(UserConstants.REGISTER_USER_TYPE);
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public int updateUser(SysUser user) throws Exception {
        return 0;
    }

    @Override
    public int updateUserInfo(SysUser user) throws Exception {
        return 0;
    }

    @Override
    public void insertUserAuth(Long userId, Long[] roleIds) throws Exception {

    }

    @Override
    public int resetUserPwd(SysUser user) throws Exception {
        return 0;
    }

    @Override
    public String checkLoginNameUnique(String loginName) throws Exception {
        return null;
    }

    @Override
    public String checkPhoneUnique(SysUser user) throws Exception {
        return null;
    }

    @Override
    public String checkEmailUnique(SysUser user) throws Exception {
        return null;
    }

    @Override
    public void checkUserAllowed(SysUser user) throws Exception {

    }

    @Override
    public String selectUserRoleGroup(Long userId) throws Exception {
        return null;
    }

    @Override
    public String selectUserPostGroup(Long userId) throws Exception {
        return null;
    }
}
