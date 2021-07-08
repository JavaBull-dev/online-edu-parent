package cn.ljpc.eduservice.service;

import cn.ljpc.eduservice.entity.EduSubject;
import cn.ljpc.eduservice.entity.vo.excel.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-18
 */
public interface EduSubjectService extends IService<EduSubject> {

    boolean saveData(MultipartFile file);

    List<OneSubject> getAllSubjectList();
}
