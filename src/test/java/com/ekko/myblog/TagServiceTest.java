package com.ekko.myblog;

import com.ekko.myblog.pojo.Tag;
import com.ekko.myblog.service.TagService;
import com.github.pagehelper.PageInfo;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TagServiceTest {
    @Autowired
    TagService tagService;

    @Test
    public void testAllTags(){
        PageInfo<Tag> pageInfo = tagService.allTags(1);
        List<Tag> list = pageInfo.getList();
        for (Tag t:list) {
            System.out.println(t);
        }
    }

    @Test
    public void testUpdatetag(){
        tagService.updateTags(1673976188198l,"大数据管理");
    }
}
