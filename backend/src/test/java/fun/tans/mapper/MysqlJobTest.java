package fun.tans.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.tans.pojo.Job;
import fun.tans.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class MysqlJobTest {
    @Autowired
    private JobService jobService;

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Test
    public void TestInsert() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
//            Job job = new Job("",
//                    getRandomString(6),
//                    random.nextInt(2000) ,
//                    getRandomString(10),
//                    getRandomString(30),
//                    random.nextInt(20000) + 20000,
//                    random.nextInt(20000) ,
//                    getRandomString(30), "admin",  DegreeEnum.DaZhuan, 1, null, null);
//            jobService.save(job);
//            Assertions.assertNotEquals("", job.getId());
        }
    }

    @Test
    public void UpdateTime() {
        List<Job> jobs = jobService.search(new HashMap<>(), new Page<>(0, 10000)).getRecords();
        for (Job job : jobs) {
            jobService.saveOrUpdate(job);
        }
    }
}
