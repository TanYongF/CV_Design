package fun.tans.controller;

import fun.tans.pojo.User;
import fun.tans.service.ResumeService;
import fun.tans.service.UserService;
import fun.tans.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CommonController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/getInfo")
    public Result<User> getInfo(@RequestHeader("token") String token) {
        User user = userService.getByToken(token);
        return Result.success(user);
    }

    /**
     * 系统所有简历分析结果
     * TODO 统计分析
     *
     * @return 所有建立的统计分析结果
     */
    @GetMapping("/analysis/all")
    public Result<HashMap<String, Object>> analysisAllCVs() {
        return Result.success(resumeService.analysisAll());
    }


}
