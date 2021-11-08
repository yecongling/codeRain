package cn.ycl.system.service.impl;

import cn.ycl.system.domain.SysPost;
import cn.ycl.system.mapper.SysPostMapper;
import cn.ycl.system.mapper.SysUserPostMapper;
import cn.ycl.system.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位信息服务层处理
 */
@Service
public class SysPostServiceImpl implements ISysPostService {

    private SysPostMapper postMapper;
    @Autowired
    public void setPostMapper(SysPostMapper postMapper) {
        this.postMapper = postMapper;
    }

    private SysUserPostMapper userPostMapper;

    @Autowired
    public void setUserPostMapper(SysUserPostMapper userPostMapper) {
        this.userPostMapper = userPostMapper;
    }

    @Override
    public List<SysPost> selectPostList(SysPost post) {
        return postMapper.selectPostList(post);
    }

    @Override
    public List<SysPost> selectPostAll() {
        return null;
    }

    @Override
    public SysPost selectPostById(Long postId) {
        return null;
    }

    @Override
    public List<Integer> selectPostListByUserId(Long userId) {
        return null;
    }

    @Override
    public String checkPostNameUnique(SysPost post) {
        return null;
    }

    @Override
    public String checkPostCodeUnique(SysPost post) {
        return null;
    }

    @Override
    public int countUserPostById(Long postId) {
        return 0;
    }

    @Override
    public int deletePostById(Long postId) {
        return 0;
    }

    @Override
    public int deletePostByIds(Long[] postIds) {
        return 0;
    }

    @Override
    public int insertPost(SysPost post) {
        return 0;
    }

    @Override
    public int updatePost(SysPost post) {
        return 0;
    }
}
