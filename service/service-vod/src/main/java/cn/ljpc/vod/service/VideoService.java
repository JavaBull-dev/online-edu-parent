package cn.ljpc.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-20 17:07
 */
public interface VideoService {

    /**
     * 上传视频
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * 删除单个视频
     * @param videoId
     */
    void removeVideoSource(String videoId);

    /**
     * 批量删除
     * @param videoList
     */
    void batchDelete(List<String> videoList);
}
