package cn.ljpc.eduservice.client;

import cn.ljpc.commonutils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-21 9:36
 */
//fallback = VodFileDegradeFeignClient.class 熔断器配置
@FeignClient(value = "service-vod", fallback = VodFileDegradeFeignClient.class)//要调用的服务名称
@Component // 交给spring管理
public interface VodClient {

    /**
     * @param videoId 阿里云视频的id，不是数据库表edu_video的主键
     * @return
     */
    @DeleteMapping(value = "/eduvod/video/{videoId}")
    CommonResult removeVideoSource(@PathVariable(value = "videoId") String videoId);

    @DeleteMapping(value = "/eduvod/video/batchDelete")
    CommonResult removeVideoSources(@RequestParam(value = "videoList") List<String> videoList);
}
