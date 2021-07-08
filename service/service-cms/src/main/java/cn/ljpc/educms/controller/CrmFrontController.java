package cn.ljpc.educms.controller;


import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.educms.entity.CrmBanner;
import cn.ljpc.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author Jacker
 * @since 2021-06-21
 */
//@CrossOrigin
@RestController
@RequestMapping("/educms/bannerfront")
public class CrmFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping(value = "/getAllBanner")
    public CommonResult getAllBanner(){
        List<CrmBanner> list = crmBannerService.getAllBanner();
        return CommonResult.ok().data("list", list);
    }
}

