package fun.tans.oss;


import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import fun.tans.conf.AliyunConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
public class OSSTest {

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliyunConfig aliyunConfig;


    @Test
    public void testConnect() {
        Assertions.assertNotNull(ossClient);
    }


    @Test
    public void testList() {
        ObjectListing files = ossClient.listObjects(aliyunConfig.getBucketName());
        for (OSSObjectSummary objectSummary : files.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " +
                    "(size = " + objectSummary.getSize() + ")");
        }
    }

    @Test
    public void testUpload() throws FileNotFoundException {
        String contentName = "C:\\Projects\\CV_Design\\backend\\src\\main\\resources\\cvs\\tyf_resume.pdf";
        File content = new File(contentName);
        String savePath = aliyunConfig.getTargetPath() + "2.pdf";
        ossClient.putObject(aliyunConfig.getBucketName(), savePath, new FileInputStream(content));
        String path = aliyunConfig.getTargetUrl() + "/" + savePath;
        System.out.println(path);
    }
}
