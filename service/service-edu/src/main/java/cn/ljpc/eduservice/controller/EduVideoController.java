package cn.ljpc.eduservice.controller;


import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.eduservice.entity.EduVideo;
import cn.ljpc.eduservice.entity.vo.VideoVo;
import cn.ljpc.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Jacker
 * @since 2021-06-19
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation(value = "根据章节ID获取小节信息")
    @GetMapping(value = "/get/{chapterId}")
    public CommonResult getAllVideo(@PathVariable("chapterId") String chapterId) {
        List<VideoVo> videoVos = eduVideoService.getAllVideo(chapterId);
        return CommonResult.ok().data("item", videoVos);
    }

    @ApiOperation(value = "添加小节")
    @PostMapping(value = "/addVideo")
    public CommonResult add(@RequestBody EduVideo video) {
        return eduVideoService.save(video) ? CommonResult.ok() : CommonResult.error();
    }

    @ApiOperation(value = "根据id删除小节")
    @DeleteMapping(value = "/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        eduVideoService.deleteVideo(id);
        return CommonResult.ok();
    }

    @ApiOperation(value = "根据id获取小节")
    @GetMapping(value = "/{id}")
    public CommonResult getById(@PathVariable("id") String id) {
        EduVideo video = eduVideoService.getById(id);
        return CommonResult.ok().data("item", video);
    }

    @ApiOperation(value = "更新小节")
    @PostMapping(value = "")
    public CommonResult update(@RequestBody EduVideo video) {
        eduVideoService.updateVideo(video);
        return CommonResult.ok();
    }
}

