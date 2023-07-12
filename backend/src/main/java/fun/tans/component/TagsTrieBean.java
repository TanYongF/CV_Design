package fun.tans.component;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.tans.mapper.TagMapper;
import fun.tans.pojo.Tag;
import fun.tans.service.TagService;
import fun.tans.tools.TrieNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagsTrieBean {

    @Autowired
    private TagMapper tagMapper;


    /**
     * 用于检索Tag的类
     *
     * @return 字典树
     */
    @Bean("tagsTrie")
    public TrieNode buildTrie() {
        TrieNode node = new TrieNode();
        List<Tag> tags = tagMapper.selectList(new QueryWrapper<>());
        for (Tag tag : tags) {
            node.insert(tag.getName());
        }
        return node;
    }

}
