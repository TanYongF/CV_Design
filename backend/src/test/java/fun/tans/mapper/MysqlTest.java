package fun.tans.mapper;

import fun.tans.pojo.User;
import fun.tans.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MysqlTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }


    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("tans");
        user.setGender((byte) 1);
        user.setPhone("16712866996");
        user.setPassword("tanyongfeng");
        user.setSalt("md555555");
        userService.save(user);
        Assertions.assertNotNull(user.getCreateAt());
    }

}