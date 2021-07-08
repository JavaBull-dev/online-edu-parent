package cn.ljpc.ucenter.controller;


import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.commonutils.JwtUtils;
import cn.ljpc.servicebase.exception.MyException;
import cn.ljpc.ucenter.entity.Member;
import cn.ljpc.ucenter.entity.vo.LoginVo;
import cn.ljpc.ucenter.entity.vo.RegisterVo;
import cn.ljpc.ucenter.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Jacker
 * @since 2021-06-22
 */
//@CrossOrigin
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginVo loginVo){
        String token = memberService.login(loginVo);
        return CommonResult.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("/register")
    public CommonResult register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return CommonResult.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/auth/getLoginInfo")
    public CommonResult getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            Member member = memberService.getById(memberId);
            return CommonResult.ok().data("item", member);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001,"error");
        }
    }
}

