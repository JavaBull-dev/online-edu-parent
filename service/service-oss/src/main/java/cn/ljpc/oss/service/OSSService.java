package cn.ljpc.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-17 17:13
 */
public interface OSSService {
    String uploadPhoto(MultipartFile file, String type);
}
