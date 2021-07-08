package cn.ljpc.msm.controller;

import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.msm.service.MsmService;
import cn.ljpc.msm.util.RandomUtil;
import cn.ljpc.servicebase.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-22 19:37
 * <p>
 * 因为在阿里云的短信服务中  添加签名 条件苛刻，无法添加签名，所以这个短信服务接口不使用
 */
@CrossOrigin
@RestController
@RequestMapping("/edumsm/msm")
@Slf4j
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send/{phoneNum}")
    public CommonResult send(@PathVariable("phoneNum") String phoneNum) {
        if (StringUtils.isEmpty(phoneNum)) {
            throw new MyException(20001, "error");
        }
        HashMap<String, Object> param = new HashMap<>();
        String code = RandomUtil.getFourBitRandom();
        param.put("code", code);//四位验证码
        boolean isSend = msmService.send2(param, phoneNum);
        //在这里模拟收到了手机验证码，在控制台中打印出来
        log.info("当前手机号：{" + phoneNum + "}的验证码为:" + param.get("code"));
        if (isSend) {
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phoneNum, code, 5, TimeUnit.MINUTES);
            return CommonResult.ok();
        } else {
            return CommonResult.error().message("短信发送失败");
        }
    }
}
