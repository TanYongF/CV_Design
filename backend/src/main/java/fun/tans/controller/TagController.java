package fun.tans.controller;


import fun.tans.pojo.Tag;
import fun.tans.service.TagService;
import fun.tans.tools.Result;
import fun.tans.tools.TrieNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TrieNode trieNode;


    @PostMapping("")
    public Result<Tag> addTag(@RequestBody Tag tag) {
        tag.setId("");
        boolean ret = tagService.save(tag);
        if (ret) trieNode.insert(tag.getName());
        return Result.success(tag);
    }


}
