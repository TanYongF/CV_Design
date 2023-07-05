package fun.tans.controller;

import fun.tans.pojo.User;
import fun.tans.service.UserService;
import fun.tans.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @Autowired
    private UserService userService;

    @GetMapping("/getInfo")
    public Result<User> getInfo(@RequestHeader("token") String token) {
        User user = userService.getByToken(token);
        return Result.success(user);
    }
}
