package fun.tans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.tans.mapper.JobMapper;
import fun.tans.mapper.ResumeMapper;
import fun.tans.pojo.Job;
import fun.tans.pojo.Resume;
import fun.tans.service.JobService;
import fun.tans.tools.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.xm.Similarity.phraseSimilarity;

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
                .ge("min_salary", params.getOrDefault("minSalary", 0))
                .ge("min_work_years", params.getOrDefault("minWorkYears", 0));
        if (params.containsKey("userName")) wrapper.eq("user_name", params.get("userName"));
        wrapper.orderByDesc("update_at");
        return jobMapper.selectPage(page, wrapper);
    }

    @Override
    public HashMap<Resume, Double> recommend(String jobId, Page<Resume> resumePage) {
        Job job = jobMapper.selectById(jobId);

        //比较硬性条件
        QueryWrapper<Resume> wrapper = new QueryWrapper<Resume>()
                .le("highest_degree", job.getMinDegree() == null ? 100 : job.getMinDegree().getCode())
                .ge("working_years", job.getMinWorkYears() == null ? -1 : job.getMinWorkYears());

        //分析词语相似度
        String s1 = job.getInfo() + " " + job.getRemark() + " " + job.getPositionName();
        HashMap<Resume, Double> mp = new HashMap<>();
        List<Resume> resumes = resumeMapper.selectPage(resumePage, wrapper).getRecords();
        if (resumes.size() == 0) return mp;
        List<Double> similarities = new ArrayList<>();
        for (Resume resume : resumes) {
            String s2 = resume.getIntention() + " " + resume.getSelfEvaluation();
            double similarity = phraseSimilarity(s1, s2);
            similarities.add(similarity);
        }
        MathUtils.Normalizer(similarities);

        for (int i = 0; i < resumes.size(); i++) mp.put(resumes.get(i), similarities.get(i));

        return mp;
    }


}

