package fun.tans.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.tans.pojo.Resume;
import fun.tans.pojo.User;
import fun.tans.service.ResumeService;
import fun.tans.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/cv")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;


    /**
     * 获取简历列表
     *
     * @param pageNo   页面偏移量
     * @param pageSize 页面大小
     * @param resume   过滤条件
     * @return 分页查询结果
     */
    @GetMapping("")
    public Result<Page<Resume>> listPageFiltering(@RequestParam("page_no") Integer pageNo,
                                                  @RequestParam("page_size") Integer pageSize,
                                                  @RequestBody(required = false) Resume resume) {
        Page<Resume> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        if (resume != null) {
            if (StringUtils.isNotEmpty(resume.getName())) {
                queryWrapper.like("name", resume.getName());
            }
            if (StringUtils.isNotEmpty(resume.getIntention())) {
                queryWrapper.like("intention", resume.getIntention());
            }
        }
        return Result.success(resumeService.page(page, queryWrapper));
    }

    /**
     * 获取某个简历
     *
     * @param resumeId 简历ID
     * @return 简历结果
     */
    @GetMapping("/{resumeId}")
    public Result<Resume> getCVById(@PathVariable("resumeId") String resumeId) {
        Resume resume = resumeService.getById(resumeId);
        return Result.success(resume);
    }


    /**
     * 处理用户上传的简历信息，并进行特定分析
     *
     * @param file 简历文件
     * @return 简历解析结果
     */
    @PostMapping("/upload")
    public Result<Resume> uploadCV(@RequestParam("file") MultipartFile file, User user) {
        Resume cv = resumeService.store(file, user);
        return Result.success(cv);
    }

    @PostMapping("/upload/v2")
    public Result<Resume> uploadCV(@RequestParam(value = "file", required = false) MultipartFile file,
                                   @RequestBody Resume resume,
                                   User user) {
        Resume cv = resumeService.store(file, user, resume);
        return Result.success(resume);
    }

    /**
     * 删除某个简历
     *
     * @param resumeId 简历ID
     * @return 删除结果
     */
    @DeleteMapping("/{resumeId}")
    public Result<Boolean> deleteCV(@PathVariable("resumeId") String resumeId) {
        boolean b = resumeService.removeById(resumeId);
        if (b) return Result.success(b);
        else return Result.error("删除错误，请刷新后重试！");
    }

    /**
     * 对特定的简历进行量化分析
     *
     * @param resumeId 简历ID
     * @return 量化分析结果
     */
    @GetMapping("/analysis/{resumeId}")
    public Result<Boolean> analysisCV(@PathVariable("resumeId") String resumeId) {
        return Result.success(resumeService.analysis(resumeId));
    }


    /**
     * 系统所有简历分析结果
     *
     * @return 所有建立的统计分析结果
     */
    @GetMapping("/analysis/all")
    public Result<Boolean> analysisAllCVs() {
        return Result.success(resumeService.analysisAll());
    }
}
