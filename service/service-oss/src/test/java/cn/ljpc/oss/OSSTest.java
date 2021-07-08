package cn.ljpc.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-17 16:55
 */
public class OSSTest {

    @Test
    public void test() {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "oss-cn-guangzhou.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5t5snay4ZAzt8y4DNEW4";
        String accessKeySecret = "P7Lqw9dfyJQLtbVxColbkjHqqNxQY3";
        String bucketName = "online-edu-2021-06";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        // 填写Bucket名称、Object完整路径和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        String filePath = "C:\\Users\\Jacker\\Desktop\\动态规划尝试模型.png";
        String fileName = UUID.randomUUID().toString().replaceAll("-", "")+"动态规划尝试模型.png";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new File(filePath));

        // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();

        // 文件url  https://online-edu-2021-06.oss-cn-guangzhou.aliyuncs.com/1.png
    }
}
