package fun.tans.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.tans.pojo.Resume;
import fun.tans.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cv")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;


    public Page<Resume> listPage(@RequestParam("page_no") Integer pageNo,
                                 @RequestParam("page_size") Integer pageSize){
        Page<Resume> page = new Page(pageNo, pageSize);
        return resumeService.page(page);
    }

    public Page<Resume> listPage(Integer pageNo, Integer pageSize, Resume resume){
        Page<Resume> page = new Page(pageNo, pageSize);
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(resume.getName())){
               queryWrapper.like("name", resume.getName());
        }
        if(StringUtils.isNotEmpty(resume.getIntention())){
            queryWrapper.like("intention", resume.getIntention());
        }

        return resumeService.page(page, queryWrapper);
    }


}
