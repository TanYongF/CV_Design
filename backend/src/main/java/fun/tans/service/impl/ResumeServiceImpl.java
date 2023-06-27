package fun.tans.service.impl;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.tans.conf.AliyunConfig;
import fun.tans.mapper.ResumeMapper;
import fun.tans.mapper.UserMapper;
import fun.tans.pojo.Resume;
import fun.tans.pojo.User;
import fun.tans.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.UUID;

@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliyunConfig aliyunConfig;

    @Autowired
    private ResumeMapper resumeMapper;

    @Override
    public Resume store(MultipartFile file) {
        Resume resume = new Resume();
        // Upload the file to the oss
        upload(file, resume);
        // Analysis the cv file, TODO

        // Insert the record into the Mysql

        resumeMapper.insert(resume);
        return resume;
    }

    //todo
    @Override
    public Boolean analysis(String resumeId) {
        return true;
    }

    //TODO
    @Override
    public Boolean analysisAll() {
        return true;
    }


    /**
     * 将文件上传OSS中，并返回其远程地址
     * @param file 待上传文件
     */
    public void upload(MultipartFile file,Resume resume) {
        String originalFilename = file.getOriginalFilename();
        //获取文件后缀名
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //新生成文件名
        //简单使用
        String fileName = UUID.randomUUID().toString() + System.currentTimeMillis() + fileSuffix;

        try(InputStream fr = file.getInputStream()) {
            String savePath = aliyunConfig.getTargetPath() + fileName;
            ossClient.putObject(aliyunConfig.getBucketName(), savePath, fr);
            resume.setFileUrl(aliyunConfig.getTargetUrl() + "/" + savePath);
            resume.setFileName(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
