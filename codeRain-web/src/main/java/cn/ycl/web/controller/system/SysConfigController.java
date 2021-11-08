package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.page.TableDataInfo;
import cn.ycl.system.domain.SysConfig;
import cn.ycl.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 参数配置controller
 */
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {

    private ISysConfigService configService;
    @Autowired
    public void setConfigService(ISysConfigService configService) {
        this.configService = configService;
    }

    /**
     * 获取参数配置列表
     * @param config 配置
     * @return 参数列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysConfig config){
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    /**
     * 根据参数键名查询参数值
     * @param configKey 参数键名
     * @return 返回参数值
     */
    @GetMapping("/configKey/{configKey}")
    public AjaxResult getConfigKey(@PathVariable String configKey){
        return AjaxResult.success(configService.selectConfigByKey(configKey));
    }
}
