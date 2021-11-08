package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.entity.SysDictType;
import cn.ycl.common.core.page.TableDataInfo;
import cn.ycl.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 获取字典数据列表
     * @param dictType 字典
     * @return 数据
     */
    @GetMapping("/list")
    public TableDataInfo list(SysDictType dictType){
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

}
