package cn.ljpc.eduservice.service.impl;

import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.eduservice.client.VodClient;
import cn.ljpc.eduservice.entity.EduVideo;
import cn.ljpc.eduservice.entity.vo.VideoVo;
import cn.ljpc.eduservice.mapper.EduVideoMapper;
import cn.ljpc.eduservice.service.EduVideoService;
import cn.ljpc.servicebase.exception.MyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-19
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public List<VideoVo> getAllVideo(String chapterId) {
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("chapter_id", chapterId);
        //升序排列
        videoWrapper.orderByAsc("sort");
        List<EduVideo> list = this.list(videoWrapper);
        ArrayList<VideoVo> ret = new ArrayList<>();

        for (EduVideo video : list) {
            VideoVo videoVo = new VideoVo();
            BeanUtils.copyProperties(video, videoVo);
            ret.add(videoVo);
        }

        return ret;
    }

    // 删除阿里云中的视频
    // 删除数据库中的记录
    @Override
    public void deleteVideo(String id) {
        boolean b1 = deleteVideoSource(id);
        if (!b1) {
            throw new MyException(20001, "删除视频失败");
        }

        boolean b = this.removeById(id);
        if (!b) {
            throw new MyException(20001, "删除视频失败");
        }
    }

    // 删除阿里云中的视频
    // 更新数据库中的数据
    @Override
    public void updateVideo(EduVideo video) {
        //视频是否要更新
        if (video.getVideoSourceId() != null) {
            boolean b = deleteVideoSource(video.getId());
            if (!b) {
                throw new MyException(20001, "更新视频失败");
            }
        }

        boolean b1 = this.updateById(video);
        if (!b1) {
            throw new MyException(20001, "更新视频失败");
        }
    }

    private boolean deleteVideoSource(String id) {
        EduVideo eduVideo = this.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        if (videoSourceId != null) {
            CommonResult commonResult = vodClient.removeVideoSource(videoSourceId);
            if (!commonResult.getSuccess()) {
                return true;
            }
        }

        return true;
    }
}
