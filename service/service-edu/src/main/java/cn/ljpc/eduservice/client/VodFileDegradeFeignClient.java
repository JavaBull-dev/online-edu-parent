package cn.ljpc.eduservice.client;

import cn.ljpc.commonutils.CommonResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-21 15:59
 */
@Component//spring容器管理  熔断器配置
public class VodFileDegradeFeignClient implements VodClient{

    //熔断后，调用的响应方法
    @Override
    public CommonResult removeVideoSource(String videoId) {
        return CommonResult.error().message("超时调用");
    }

    //熔断后，调用的响应方法
    @Override
    public CommonResult removeVideoSources(List<String> videoList) {
        return CommonResult.error().message("超时调用");
    }
}
