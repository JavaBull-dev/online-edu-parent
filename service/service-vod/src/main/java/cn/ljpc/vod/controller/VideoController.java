package cn.ljpc.vod.controller;

import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.servicebase.exception.MyException;
import cn.ljpc.vod.service.VideoService;
import cn.ljpc.vod.util.ConstantPropertiesUtil;
import cn.ljpc.vod.util.InitVodCilent;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-20 17:10
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/eduvod/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "视频上传")
    @PostMapping(value = "/upload")
    public CommonResult updateVideo(MultipartFile file) {
        String videoId = videoService.uploadVideo(file);
        return CommonResult.ok().data("videoId", videoId);
    }

    @ApiOperation(value = "删除单个云视频")
    @DeleteMapping(value = "/{videoId}")
    public CommonResult removeVideoSource(@PathVariable(value = "videoId") String videoId) {
        videoService.removeVideoSource(videoId);
        return CommonResult.ok();
    }

    @ApiOperation(value = "删除多个云视频")
    @DeleteMapping(value = "/batchDelete")
    public CommonResult removeVideoSources(@RequestParam(value = "videoList") List<String> videoList){
        videoService.batchDelete(videoList);
        return CommonResult.ok();
    }

    @ApiOperation(value = "获取播放凭证")
    @GetMapping("/getPlayAuth/{videoId}")
    public CommonResult getPlayAuth(@PathVariable(value = "videoId") String videoId){
        try {
            //创建初始化对象
            DefaultAcsClient client =
                    InitVodCilent.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(videoId);
            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return CommonResult.ok().data("playAuth",playAuth);
        }catch(Exception e) {
            throw new MyException(20001,"获取凭证失败");
        }
    }
}