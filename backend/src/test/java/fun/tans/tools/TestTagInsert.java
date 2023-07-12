package fun.tans.tools;


import cn.hutool.core.io.resource.ClassPathResource;
import fun.tans.pojo.Tag;
import fun.tans.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestTagInsert {

    @Autowired
    private TagService tagService;

    @Test
    public void insertTagToMysql() throws FileNotFoundException {
        TrieNode trie = new TrieNode();
        ClassPathResource classPathResource = new ClassPathResource("cvs/tag.txt");
        File file = classPathResource.getFile();
        System.out.println(file.getAbsolutePath());
//        FileReader reader = null;
        try (FileReader reader = new FileReader(file); BufferedReader in = new BufferedReader(reader)) {
            String str = null;
            List<Tag> tags = new ArrayList<>();
            while ((str = in.readLine()) != null) {
                trie.insert(str);
                tags.add(new Tag("", str));
            }
            tagService.saveBatch(tags);
//            String word = trie.searchWord("Javaer");
//            if(word != null) System.out.println(word);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
