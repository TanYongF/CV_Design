package fun.tans.service;


import com.baomidou.mybatisplus.extension.service.IService;
import fun.tans.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService extends IService<User> {
    String COOKIE_NAME_TOKEN = "token";
    String TOKEN_PREFIX = "cv:token:";

    Boolean isAuth(String token);

    void register(User user);

    String login(User user);

    void update(User user);

    void logout(String token);

    User getByToken(String token);

    String uploadAvatar(MultipartFile file);
}
