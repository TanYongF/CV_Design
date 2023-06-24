package fun.tans.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.tans.mapper.ResumeMapper;
import fun.tans.mapper.UserMapper;
import fun.tans.pojo.Resume;
import fun.tans.pojo.User;
import fun.tans.service.ResumeService;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {

}
