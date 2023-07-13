package fun.tans.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.aliyun.oss.OSS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.tans.conf.AliyunConfig;
import fun.tans.mapper.JobMapper;
import fun.tans.mapper.ResumeMapper;
import fun.tans.mapper.TagMapper;
import fun.tans.mapper.UserMapper;
import fun.tans.pojo.Resume;
import fun.tans.pojo.User;
import fun.tans.service.ResumeService;
import fun.tans.tools.TrieNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliyunConfig aliyunConfig;

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private TrieNode tagSearchTrie;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JobMapper jobMapper;

    @Override
    public Resume store(MultipartFile file, User user) {
        Resume resume = new Resume();
        resume.setUploadUser(user.getUsername());
        // Upload the file to the oss
        upload(file, resume);
        // Analysis the cv file, TODO

        // Insert the record into the Mysql
        resumeMapper.insert(resume);
        return resume;
    }


    @Override
    public Resume store(MultipartFile file, User user, Resume resume) {
        resume.setUploadUser(user.getUsername());
        if (file != null) upload(file, resume);
        saveOrUpdate(resume);
        return resume;
    }

    //todo
    @Override
    public Boolean analysis(String resumeId) {
        return true;
    }

    //TODO
    @Override
    public HashMap<String, Object> analysisAll() {
        HashMap<String, Object> mp = new HashMap<>();
        LambdaQueryWrapper<Resume> queryMapper = new LambdaQueryWrapper<>();
        Long resumeCount = resumeMapper.selectCount(new QueryWrapper<>());
        Long userCount = userMapper.selectCount(new QueryWrapper<>());
        Long tagCount = tagMapper.selectCount(new QueryWrapper<>());
        Long jobCount = jobMapper.selectCount(new QueryWrapper<>());
        // the total resume nums
        mp.put("resumeCount", resumeCount);
        mp.put("tagCount", tagCount);
        mp.put("userCount", userCount);
        mp.put("jobCount", jobCount);
        Map<Object, Long> schools = format(resumeMapper.countByColumnKey("school"), "school", "count");
        Map<Object, Long> genders = format(resumeMapper.countByColumnKey("gender"), "gender", "count");
        Map<Object, Long> degrees = format(resumeMapper.countByColumnKey("highest_degree"), "highest_degree", "count");
        Map<Object, Long> ages =  format(resumeMapper.countByColumnKey("age"), "age", "count");
        mp.put("schools", schools);
        mp.put("genders", genders);
        mp.put("degrees", degrees);
        mp.put("ages", ages);

        return mp;
    }


    /**
     * 将文件上传OSS中，并返回其远程地址
     *
     * @param file 待上传文件
     */
    public void upload(MultipartFile file, Resume resume) {
        String originalFilename = file.getOriginalFilename();
        //获取文件后缀名
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //新生成文件名
        //简单使用
        String fileName = UUID.randomUUID().toString() + System.currentTimeMillis() + fileSuffix;
        try (InputStream fr = file.getInputStream()) {
            String savePath = aliyunConfig.getTargetPath() + fileName;
            ossClient.putObject(aliyunConfig.getBucketName(), savePath, fr);
            resume.setFileUrl(aliyunConfig.getTargetUrl() + "/" + savePath);
            resume.setFileName(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Map<Object, Long> format(List<Map<String, Long>> list, String keyName, String valueName){
        HashMap<Object, Long> mp = new HashMap<>();
        if(CollectionUtil.isNotEmpty(list)) {
            for(Map item : list){

                mp.put(item.get(keyName), (Long) item.get(valueName));
            }

        }
        return mp;
    }
}
