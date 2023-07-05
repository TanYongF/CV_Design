package fun.tans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.tans.mapper.JobMapper;
import fun.tans.mapper.ResumeMapper;
import fun.tans.pojo.Job;
import fun.tans.pojo.Resume;
import fun.tans.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("jobService")
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private ResumeMapper resumeMapper;


    @Override
    public Page<Job> search(Map<String, Object> params, Page<Job> page) {
        QueryWrapper<Job> wrapper = new QueryWrapper<>();
        wrapper.like("position_name", params.getOrDefault("positionName", "%"))
                .like("company_name", params.getOrDefault("companyName", "%"))
                .ge("min_salary", params.getOrDefault("minSalary", 0));
        if (params.containsKey("userName")) wrapper.eq("user_name", params.get("userName"));
        return jobMapper.selectPage(page, wrapper);
    }

    @Override
    public Page<Resume> recommend(String jobId, Page<Resume> resumePage) {
        Job job = jobMapper.selectById(jobId);
        QueryWrapper<Resume> wrapper = new QueryWrapper<>();
        wrapper.le("highest_degree", job.getMinDegree() == null ? 100 : job.getMinDegree().getCode())
                .ge("working_years", job.getMinWorkYears() == null ? -1 : job.getMinWorkYears());
        return resumeMapper.selectPage(resumePage, wrapper);
    }
}
