package fun.tans.service;


import com.baomidou.mybatisplus.extension.service.IService;
import fun.tans.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
    String COOKIE_NAME_TOKEN = "token";
    String TOKEN_PREFIX = "cv:token:";

    Boolean isAuth(String token);

    void register(User user);

    User login(User user);

    void update(User user);

}
