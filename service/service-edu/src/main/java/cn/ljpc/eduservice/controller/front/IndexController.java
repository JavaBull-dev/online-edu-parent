package cn.ljpc.eduservice.controller.front;

import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.eduservice.entity.EduCourse;
import cn.ljpc.eduservice.entity.EduTeacher;
import cn.ljpc.eduservice.service.EduCourseService;
import cn.ljpc.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-21 20:42
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping("/index")
    public CommonResult index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduList = courseService.list(wrapper);
        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        return CommonResult.ok().data("eduList", eduList).data("teacherList", teacherList);
    }
}
