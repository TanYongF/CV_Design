package fun.tans.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import fun.tans.annotation.NeedAuth;
import fun.tans.pojo.User;
import fun.tans.service.UserService;
import fun.tans.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;


    @PostMapping("/register")
    public Result<User> register(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam(value = "admin", required = false) Integer isAdmin) {
        User user = new User(username, password);
        user.setRole(isAdmin == 1);
        userService.register(user);
        return Result.success(user);
    }

    /**
     * 根据特定条件查找用户
     * @param username 用户名
     * @param nickname 用户昵称
     * @param gender   用户性别
     * @param phone    用户手机号
     * @return 所有符合条件的用户
     */
    @GetMapping("")
    @NeedAuth
    public Result<List<User>> getUser(@RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "nickname", required = false) String nickname,
                                @RequestParam(value = "gender",required = false) Boolean gender,
                                @RequestParam(value = "phone",required = false) String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("nickname", nickname);
        map.put("gender", gender);
        map.put("phone", phone);
        wrapper.allEq(map, false);
        List<User> users = userService.list(wrapper);
        return Result.success(users);
    }

    @PostMapping("/login")
    public Result<User> login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        User formUser = new User(username, password);
        User user = userService.login(formUser);
        return Result.success(user);
    }


    /**
     * update the user's info
     * @param username 用户名
     * @param nickname 昵称
     * @param gender   年龄
     * @param phone    电话
     * @return 用户
     */
    @PutMapping("")
    @NeedAuth
    public Result<User> update(@RequestParam("username") String username,
                               @RequestParam(value = "nickname", required = false) String nickname,
                               @RequestParam(value = "gender",required = false) Boolean gender,
                               @RequestParam(value = "phone",required = false) String phone){
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setGender(gender);
        user.setPhone(phone);
        userService.update(user);
        return Result.success(user);
    }
}
