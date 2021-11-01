package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.entity.SysDictData;
import cn.ycl.common.utils.StringUtils;
import cn.ycl.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {

    private ISysDictTypeService dictTypeService;
    @Autowired
    public void setDictTypeService(ISysDictTypeService dictTypeService) {
        this.dictTypeService = dictTypeService;
    }

    /**
     * 根据字典类型查询字典数据信息
     * @param dictType 字典类型
     * @return 返回字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType){
        List<SysDictData> list = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(list)){
            list = new ArrayList<>();
        }
        return AjaxResult.success(list);
    }
}
