package fun.tans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.tans.mapper.ResumeTagMapper;
import fun.tans.mapper.TagMapper;
import fun.tans.pojo.DTO.ResumeTag;
import fun.tans.pojo.Resume;
import fun.tans.pojo.Tag;
import fun.tans.service.TagService;
import fun.tans.tools.TrieNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TrieNode trieNode;


    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ResumeTagMapper resumeTagMapper;


    @Override
    public void analysis(Resume cv) {
        //delete the former record
        resumeTagMapper.delete(new QueryWrapper<ResumeTag>().eq("resume_id", cv.getId()));
        //insert the new records
        HashSet<String> words = new HashSet<>();
        String info = cv.getIntention() + " " + cv.getSelfEvaluation();
        for (int i = 0; i < info.length(); i++) {
            //获取所有匹配的标签
            String word = trieNode.searchWord(info.substring(i));
            if (StringUtils.hasText(word)) words.add(word);
        }

        for (String word : words) {
            QueryWrapper<Tag> wrapper = new QueryWrapper<>();
            wrapper.eq("name", word);
            Tag tag = tagMapper.selectOne(wrapper);
            ResumeTag resumeTag = new ResumeTag(cv.getId(), tag.getId());
            // insert this relation to the table
            resumeTag.insert();
        }
    }
}
