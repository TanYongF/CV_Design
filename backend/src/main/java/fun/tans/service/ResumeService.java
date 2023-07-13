package fun.tans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.tans.pojo.Resume;
import fun.tans.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface ResumeService extends IService<Resume> {
    Resume store(MultipartFile file, User user);

    Resume store(MultipartFile file, User user, Resume resume);

    Boolean analysis(String resumeId);

    HashMap<String, Object> analysisAll();
}
