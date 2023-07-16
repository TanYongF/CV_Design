package fun.tans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.tans.pojo.Resume;
import fun.tans.pojo.Tag;

public interface TagService extends IService<Tag> {
    void analysis(Resume cv);
}
