package cn.ljpc.eduservice.service;

import cn.ljpc.eduservice.entity.EduChapter;
import cn.ljpc.eduservice.entity.vo.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-19
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getByCourseId(String cid);

    void deleteCourse(String chapterId);

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
