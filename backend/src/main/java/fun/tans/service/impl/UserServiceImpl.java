package fun.tans.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.tans.exception.BizException;
import fun.tans.mapper.UserMapper;
import fun.tans.pojo.User;
import fun.tans.service.UserService;
import fun.tans.tools.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


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
    public User login(User formUser) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", formUser.getUsername());
        User dbUser = userMapper.selectOne(wrapper);
        if (dbUser == null || !SecurityUtil.crypto(dbUser.getSalt(), formUser.getPassword()).equals(dbUser.getPassword()))
            throw new BizException("501", "账号或密码错误！");
        //todo: generate token to user
        String token = IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(TOKEN_PREFIX + token, JSONUtil.toJsonStr(dbUser), 12, TimeUnit.HOURS);
        return dbUser;
    }

    @Override
    public void update(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(User::getUsername, user.getUsername());
        update(user,updateWrapper);
    }
}
