package fun.tans.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.tans.conf.AliyunConfig;
import fun.tans.exception.BizException;
import fun.tans.mapper.UserMapper;
import fun.tans.pojo.User;
import fun.tans.service.UserService;
import fun.tans.tools.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private AliyunConfig aliyunConfig;

    @Autowired
    private OSS ossClient;


    @Override
    public Boolean isAuth(String token) {
        return redisTemplate.hasKey(TOKEN_PREFIX + token);
    }

    @Override
    public void register(User user) {
        String salt = IdUtil.simpleUUID();
        String password = SecurityUtil.crypto(salt, user.getPassword());
        user.setPassword(password);
        user.setSalt(salt);
        user.setNickname(user.getUsername());
        user.setGender(false);
        userMapper.insert(user);
    }

    @Override
    public String login(User formUser) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", formUser.getUsername());
        User dbUser = userMapper.selectOne(wrapper);
        if (dbUser == null || !SecurityUtil.crypto(dbUser.getSalt(), formUser.getPassword()).equals(dbUser.getPassword()))
            throw new BizException("501", "账号或密码错误！");
        //todo: generate token to user
        String token = IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(TOKEN_PREFIX + token, JSONUtil.toJsonStr(dbUser), 12, TimeUnit.HOURS);
        return token;
    }

    @Override
    public void update(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(User::getUsername, user.getUsername());
        update(user, updateWrapper);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(TOKEN_PREFIX + token);
    }

    @Override
    public User getByToken(String token) {
        Object o = redisTemplate.opsForValue().get(TOKEN_PREFIX + token);
        if (o == null) {
            return null;
        } else {
            return JSONUtil.toBean((String) o, User.class);
        }
    }

    @Override
    public String uploadAvatar(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //获取文件后缀名
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //新生成文件名
        //简单使用
        String fileName = UUID.randomUUID().toString() + System.currentTimeMillis() + fileSuffix;
        String targetPath;
        try (InputStream fr = file.getInputStream()) {
            String savePath = aliyunConfig.getTargetPath() + fileName;
            ossClient.putObject(aliyunConfig.getBucketName(), savePath, fr);
            targetPath = aliyunConfig.getTargetUrl() + "/" + savePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return targetPath;
    }
}
