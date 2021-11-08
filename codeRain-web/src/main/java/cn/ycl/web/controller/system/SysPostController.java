package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.page.TableDataInfo;
import cn.ycl.system.domain.SysPost;
import cn.ycl.system.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 岗位信息controller
 */
@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController {

    private ISysPostService postService;
    @Autowired
    public void setPostService(ISysPostService postService) {
        this.postService = postService;
    }

    /**
     * 获取岗位列表
     * @param post 岗位筛选条件
     * @return 岗位信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysPost post){
        startPage();
        List<SysPost> posts = postService.selectPostList(post);
        return getDataTable(posts);
    }
}
