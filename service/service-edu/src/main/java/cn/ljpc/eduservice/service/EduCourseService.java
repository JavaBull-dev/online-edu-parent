package cn.ljpc.eduservice.service;

import cn.ljpc.eduservice.entity.EduCourse;
import cn.ljpc.eduservice.entity.frontvo.CourseFrontVo;
import cn.ljpc.eduservice.entity.frontvo.CourseWebVo;
import cn.ljpc.eduservice.entity.publish.CoursePublishVo;
import cn.ljpc.eduservice.entity.vo.course.CourseInfoForm;
import cn.ljpc.eduservice.entity.vo.course.CourseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-19
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoFormById(String courseId);

    void updateCourseInfo(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishInfo(String id);

    boolean publishCourse(String id);

    void pageCourseInfo(Page<EduCourse> pageParam, CourseQuery courseQuery);

    void removeCourse(String id);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
