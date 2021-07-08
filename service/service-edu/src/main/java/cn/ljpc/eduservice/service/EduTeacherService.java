package cn.ljpc.eduservice.service;

import cn.ljpc.eduservice.entity.EduTeacher;
import cn.ljpc.eduservice.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-14
 */
public interface EduTeacherService extends IService<EduTeacher> {

    IPage<EduTeacher> saveTeacher(Page<EduTeacher> teacherPage, TeacherQuery teacherQuery);

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
