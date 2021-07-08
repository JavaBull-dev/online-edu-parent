package cn.ljpc.eduservice.controller.front;

import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.eduservice.entity.EduCourse;
import cn.ljpc.eduservice.entity.frontvo.CourseFrontVo;
import cn.ljpc.eduservice.entity.frontvo.CourseWebVo;
import cn.ljpc.eduservice.entity.vo.chapter.ChapterVo;
import cn.ljpc.eduservice.service.EduChapterService;
import cn.ljpc.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public CommonResult getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                           @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return CommonResult.ok().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public CommonResult getFrontCourseInfo(@PathVariable String courseId) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        return CommonResult.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }
}