package cn.ljpc.eduservice.service;

import cn.ljpc.eduservice.entity.EduVideo;
import cn.ljpc.eduservice.entity.vo.VideoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-19
 */
public interface EduVideoService extends IService<EduVideo> {

    List<VideoVo> getAllVideo(String chapterId);

    void deleteVideo(String id);

    void updateVideo(EduVideo video);
}
