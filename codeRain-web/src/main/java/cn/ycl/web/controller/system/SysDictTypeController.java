package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据类型字典信息
 */
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController {

    private ISysDictTypeService dictTypeService;
    @Autowired
    public void setDictTypeService(ISysDictTypeService dictTypeService) {
        this.dictTypeService = dictTypeService;
    }

}
