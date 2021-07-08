package cn.ljpc.eduservice.controller.front;

import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.eduservice.entity.EduCourse;
import cn.ljpc.eduservice.entity.EduTeacher;
import cn.ljpc.eduservice.service.EduCourseService;
import cn.ljpc.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //1 分页查询讲师的方法
    @PostMapping("/getTeacherFrontList/{page}/{limit}")
    public CommonResult getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(pageTeacher);
        //返回分页所有数据
        return CommonResult.ok().data(map);
    }

    //2 讲师详情的功能
    @GetMapping("/getTeacherFrontInfo/{teacherId}")
    public CommonResult getTeacherFrontInfo(@PathVariable String teacherId) {
        //1 根据讲师id查询讲师基本信息
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        //2 根据讲师id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        return CommonResult.ok().data("teacher",eduTeacher).data("courseList",courseList);
    }
}












