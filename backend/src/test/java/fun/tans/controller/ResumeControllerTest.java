package fun.tans.controller;

import fun.tans.CVApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CVApplication.class)
public class ResumeControllerTest {
    /**
     *  注入一个web应用环境(容器)
     */
    @Resource
    private WebApplicationContext webApplicationContext;
    //mvc 环境对象
    private MockMvc mockMvc;
    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void findObject() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post('/resume/upload/v2').headers("token", ))
    }
}