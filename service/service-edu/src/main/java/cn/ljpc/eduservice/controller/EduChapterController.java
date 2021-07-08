package cn.ljpc.eduservice.controller;


import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.eduservice.entity.EduChapter;
import cn.ljpc.eduservice.entity.vo.chapter.ChapterVo;
import cn.ljpc.eduservice.service.EduChapterService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Jacker
 * @since 2021-06-19
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "根据课程ID获取章节信息")
    @GetMapping(value = "/getChapter/{cid}")
    public CommonResult getAllChapterById(@PathVariable("cid") String cid) {
        List<ChapterVo> list = eduChapterService.getByCourseId(cid);
        return CommonResult.ok().data("item", list);
    }

    @ApiOperation(value = "删除章节")
    @DeleteMapping(value = "/{chapterId}")
    public CommonResult remove(@PathVariable("chapterId") String chapterId) {
        eduChapterService.deleteCourse(chapterId);
        return CommonResult.ok();
    }


    @ApiOperation(value = "添加章节")
    @PostMapping(value = "")
    public CommonResult save(@RequestBody EduChapter eduChapter) {
        return eduChapterService.save(eduChapter)
                ? CommonResult.ok()
                : CommonResult.error();
    }

    @ApiOperation(value = "根据章节id获取章节")
    @GetMapping(value = "/{id}")
    public CommonResult getById(@PathVariable("id") String chapterId) {
        EduChapter chapter = eduChapterService.getById(chapterId);
        return CommonResult.ok().data("chapter", chapter);
    }

    @ApiOperation(value = "根据章节id更新章节")
    @PostMapping(value = "/{id}")
    public CommonResult updateById(@RequestBody EduChapter eduChapter) {
        log.info(eduChapter.toString());
        return eduChapterService.updateById(eduChapter) ? CommonResult.ok() : CommonResult.error();
    }
}