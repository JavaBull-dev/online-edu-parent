package cn.ljpc.oss.controller;

import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.oss.service.OSSService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-17 17:13
 */
//@CrossOrigin//跨域
@RestController
@RequestMapping(value = "/eduoss/file")
public class OSSController {

    @Autowired
    private OSSService ossService;

    //文件上传
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public CommonResult uploadAvatar(MultipartFile file) {
        String url = ossService.uploadPhoto(file, null);
        return url == null ? CommonResult.error() : CommonResult.ok().data("url", url);
    }

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload/{type}")
    public CommonResult uploadOther(MultipartFile file,
                                     @PathVariable(value = "type") String type) {
        String url = ossService.uploadPhoto(file, type);
        return url == null ? CommonResult.error() : CommonResult.ok().data("url", url);
    }
}
