package cn.ljpc.educms.controller;

import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.educms.entity.CrmBanner;
import cn.ljpc.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * banner 的后台管理接口
 *
 * @author Jacker
 * @Description
 * @create 2021-06-21 21:28
 */

@RestController
@RequestMapping("/educms/banneradmin")
//@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    //1 分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public CommonResult pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        bannerService.page(pageBanner, null);
        return CommonResult.ok().data("items", pageBanner.getRecords()).data("total", pageBanner.getTotal());
    }

    //2 添加banner
    @PostMapping("addBanner")
    public CommonResult addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return CommonResult.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public CommonResult get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return CommonResult.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public CommonResult updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return CommonResult.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public CommonResult remove(@PathVariable String id) {
        bannerService.removeById(id);
        return CommonResult.ok();
    }
}
