package fun.tans.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.tans.pojo.DTO.ResumeTag;
import fun.tans.pojo.Resume;
import fun.tans.pojo.Tag;
import fun.tans.pojo.User;
import fun.tans.service.ResumeService;
import fun.tans.service.TagService;
import fun.tans.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cv")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private TagService tagService;


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
                                                  @RequestBody(required = false) Resume resume,
                                                  User user) {
        Page<Resume> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        //如果是普通用户， 那么获取她自己的简历
        if (!user.getRole()) queryWrapper.eq("upload_user", user.getUsername());
        if (resume != null) {
            if (StringUtils.isNotEmpty(resume.getName())) {
                queryWrapper.like("name", resume.getName());
            }
            if (StringUtils.isNotEmpty(resume.getIntention())) {
                queryWrapper.like("intention", resume.getIntention());
            }
        }
        //根据时间降序排序
        queryWrapper.orderByDesc("update_at");
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
        // analysis the resume's tag
        tagService.analysis(cv);
        return Result.success(cv);
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


    @PostMapping("/tag")
    public Result<Boolean> addTag(@RequestBody ResumeTag resumeTag) {
        return Result.success(resumeTag.insert());
    }

    @DeleteMapping("/tag")
    public Result<Boolean> removeTag(@RequestBody ResumeTag resumeTag) {
        QueryWrapper<ResumeTag> wrapper = new QueryWrapper<>();
        wrapper.eq("resume_id", resumeTag.getResumeId())
                .eq("tag_id", resumeTag.getTagId());
        return Result.success(resumeTag.delete(wrapper));
    }

    @GetMapping("/tag/{resumeId}")
    public Result<List<Tag>> getTags(@PathVariable("resumeId") String resumeId) {
        QueryWrapper<ResumeTag> wrapper = new QueryWrapper<>();
        wrapper.eq("resume_id", resumeId);
        ResumeTag resumeTag = new ResumeTag();
        List<ResumeTag> resumeTags = resumeTag.selectList(wrapper);
        List<String> tagIds = resumeTags.stream().map(ResumeTag::getTagId).collect(Collectors.toList());
        if (tagIds.isEmpty()) return Result.success(null);
        QueryWrapper<Tag> wrapper1 = new QueryWrapper<Tag>().in("id", tagIds);
        return Result.success(tagService.list(wrapper1));
    }


}
