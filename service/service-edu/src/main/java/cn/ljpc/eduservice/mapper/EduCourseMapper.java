package cn.ljpc.eduservice.mapper;

import cn.ljpc.eduservice.entity.EduCourse;
import cn.ljpc.eduservice.entity.frontvo.CourseWebVo;
import cn.ljpc.eduservice.entity.publish.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Jacker
 * @since 2021-06-19
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getCourseInfoById(@Param("id") String id);

    CourseWebVo getBaseCourseInfo(@Param("courseId") String courseId);
}
