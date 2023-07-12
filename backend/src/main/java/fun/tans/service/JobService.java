package fun.tans.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.tans.pojo.Job;
import fun.tans.pojo.Resume;

import java.util.HashMap;
import java.util.Map;

public interface JobService extends IService<Job> {
    Page<Job> search(Map<String, Object> params, Page<Job> page);

    HashMap<Resume, Double> recommend(String jobId, Page<Resume> resumePage);
}
