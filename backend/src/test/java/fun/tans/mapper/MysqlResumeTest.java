package fun.tans.mapper;

import fun.tans.enums.DegreeEnum;
import fun.tans.pojo.Resume;
import fun.tans.pojo.User;
import fun.tans.service.ResumeService;
import fun.tans.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MysqlResumeTest {

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private ResumeService resumeService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Resume> resumeList = resumeMapper.selectList(null);
        Assertions.assertEquals(5, resumeList.size());
        resumeList.forEach(System.out::println);
    }


    @Test
    public void testInsert() {
        Resume resume = new Resume();
        resume.setFileUrl("https://tans.fun");
        resume.setFileName("101.pdf");
        resume.setName("tan yongfeng");
        resume.setHighestDegree(DegreeEnum.Doctor);
        resume.setTel("12345678910");
        resume.setWechat("tan13621251388132");
        resume.setEmail("tan13621251388@gmail.com");
        resume.setAge((short) 21);
        resume.setSchool("南京邮电大学");
        resume.setIntention("后端开发工程师");
        resume.setGender(Boolean.TRUE);
        resume.setAvatarUrl("https://tans.fun/avatar");
        resume.setWorkingYears((short) 3);
        resumeService.save(resume);
        Assertions.assertNotNull(resume.getCreateAt());
    }

}