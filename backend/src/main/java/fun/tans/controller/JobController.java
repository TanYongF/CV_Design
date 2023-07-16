package fun.tans.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.tans.pojo.Job;
import fun.tans.pojo.Resume;
import fun.tans.pojo.User;
import fun.tans.service.JobService;
import fun.tans.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * The controllers related to the job manage
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;


    @GetMapping("/{jobId}")
    public Result<Job> getJobById(@PathVariable("jobId") String jobId) {
        return Result.success(jobService.getById(jobId));
    }

    @DeleteMapping("/{jobId}")
    public Result<Boolean> deleteJobById(@PathVariable("jobId") String jobId) {
        boolean result = jobService.removeById(jobId);
        return Result.success(result);
    }

    /**
     * insert the new job or update the job(if the param contains id properties)
     *
     * @param job entity which should insert or update
     * @return the new jobs
     */
    @PostMapping("")
    public Result<Job> saveJob(@RequestBody Job job, User user) {
        job.setId("");
        job.setUserName(user.getUsername());
        jobService.saveOrUpdate(job);
        return Result.success(job);
    }

    @PostMapping("/search")
    public Result<Page<Job>> searchJob(@RequestParam("page_no") Integer pageNo,
                                       @RequestParam("page_size") Integer pageSize,
                                       @RequestBody(required = false) Map<String, Object> params) {
        Page<Job> page = new Page<>(pageNo, pageSize);
        Page<Job> filterJobs = jobService.search(params, page);
        return Result.success(filterJobs);
    }

    /**
     * 根据岗位条件推荐候选人
     *
     * @param jobId 岗位编号
     * @return
     */
    @GetMapping("/propose/{jobId}")
    public Result<ArrayList<HashMap<String, Object>>> recommend(@RequestParam("page_no") Integer pageNo,
                                                                @RequestParam("page_size") Integer pageSize,
                                                                @PathVariable("jobId") String jobId) {
        HashMap<Resume, Double> ret = jobService.recommend(jobId, new Page<>(pageNo, pageSize));
        ArrayList<Map.Entry<Resume, Double>> entries = new ArrayList<>(ret.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (Map.Entry entry : entries) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("key", entry.getKey());
            map.put("value", entry.getValue());
            list.add(map);
        }
        return Result.success(list);
    }
}
