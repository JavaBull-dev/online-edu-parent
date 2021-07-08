package cn.ljpc.eduservice.controller;


import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.eduservice.entity.EduCourse;
import cn.ljpc.eduservice.entity.publish.CoursePublishVo;
import cn.ljpc.eduservice.entity.vo.course.CourseInfoForm;
import cn.ljpc.eduservice.entity.vo.course.CourseQuery;
import cn.ljpc.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "新增课程")
    @PostMapping("/saveCourseInfo")
    public CommonResult saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {

        String courseId = eduCourseService.saveCourseInfo(courseInfoForm);
        if (!StringUtils.isEmpty(courseId)) {
            return CommonResult.ok().data("courseId", courseId);
        } else {
            return CommonResult.error().message("保存失败");
        }
    }

    @ApiOperation(value = "通过课程ID获取课程信息")
    @GetMapping("/getCourseInfo/{id}")
    public CommonResult getCourseInfo(@PathVariable("id") String courseId) {
        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfoFormById(courseId);
        return CommonResult.ok().data("courseInfo", courseInfoForm);
    }

    @ApiOperation(value = "更新课程")
    @PostMapping("/updateCourseInfo/{id}")
    public CommonResult updateCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm,
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable("id") String id) {
        eduCourseService.updateCourseInfo(courseInfoForm);
        return CommonResult.ok();
    }

    @ApiOperation(value = "获取课程的信息")
    @GetMapping("/coursePublishInfo/{id}")
    public CommonResult getCoursePublishInfoById(@PathVariable("id") String id) {
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishInfo(id);
        return CommonResult.ok().data("item", coursePublishVo);
    }

    @ApiOperation(value = "发布课程")
    @PutMapping("/publishCourse/{id}")
    public CommonResult publishCourse(@PathVariable("id") String id) {
        return eduCourseService.publishCourse(id) ? CommonResult.ok() : CommonResult.error();
    }

    @ApiOperation(value = "分页查询课程")
    @GetMapping("/{page}/{limit}")
    public CommonResult pageCourseInfo(@PathVariable("page") Integer page,
                                       @PathVariable("limit") Integer limit, CourseQuery courseQuery) {
        Page<EduCourse> pageParam = new Page<>(page, limit);
        eduCourseService.pageCourseInfo(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        return CommonResult.ok().data("rows", records).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "删除课程")
    @DeleteMapping("/{id}")
    public CommonResult removeCourse(@PathVariable("id") String id) {
        eduCourseService.removeCourse(id);
        return CommonResult.ok();
    }
}