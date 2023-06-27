package fun.tans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.tans.pojo.Resume;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeService extends IService<Resume> {
    Resume store(MultipartFile file);

    Boolean analysis(String resumeId);

    Boolean analysisAll();
}
