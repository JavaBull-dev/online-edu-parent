package cn.ljpc.eduservice.controller;

import cn.ljpc.commonutils.CommonResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-16 20:49
 */
@RestController
@CrossOrigin //解决跨域问题
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("/login")
    public CommonResult login(){
        return CommonResult.ok().data("token", "jskdfjlejrklsjaieksk");
    }

    @GetMapping("/info")
    public CommonResult info(){
        return CommonResult.ok()
                .data("roles", "[admin]")
                .data("name", "jacker")
                .data("avatar", "https://static01.imgkr.com/temp/6cabbfd468b7410c94b8b3aaa04e89ff.png");
    }
}
