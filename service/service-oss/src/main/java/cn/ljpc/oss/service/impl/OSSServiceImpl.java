package cn.ljpc.oss.service.impl;

import cn.ljpc.oss.service.OSSService;
import cn.ljpc.oss.utils.OSSConstantProperty;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-17 17:14
 */
@Service
//@Slf4j
public class OSSServiceImpl implements OSSService {

    @Override
    public String uploadPhoto(MultipartFile file, String type) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = OSSConstantProperty.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = OSSConstantProperty.ACCESS_KEY_ID;
        String accessKeySecret = OSSConstantProperty.ACCESS_KEY_SECRET;
        String bucketName = OSSConstantProperty.BUCKET_NAME;

        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 创建PutObjectRequest对象。
            // 填写Bucket名称、Object完整路径和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
            // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
            InputStream inputStream = file.getInputStream();

            //文件路径  2020/10/10/sdjfkalsf23948jsdf01.jpg
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename();
            fileName = new DateTime().toString("yyyy/MM/dd") + "/" + fileName;
//            log.info("文件名："+fileName);
            if (type != null) {
                fileName = type.replaceAll("/", "") + "/" + fileName;
            }
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);

            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传文件。
            ossClient.putObject(putObjectRequest);

            // 文件url  https://online-edu-2021-06.oss-cn-guangzhou.aliyuncs.com/1.png
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }
}
