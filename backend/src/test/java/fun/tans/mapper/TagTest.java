package fun.tans.mapper;


import fun.tans.pojo.Tag;
import fun.tans.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TagTest {


    @Autowired
    private TagService tagService;

    @Test
    public void getAllTags() {
        List<Tag> tags = tagService.list();
        for (Tag tag : tags) {
            System.out.println(tag);
        }
    }
}
