package com.ekko.myblog;

import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.BlogTag;
import com.ekko.myblog.service.BlogService;
import com.ekko.myblog.service.BlogTagService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BlogServiceTest {
    @Autowired
    BlogService blogService;
    @Test
    public void testTimeStampAPI(){
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        System.out.println(
                timestamp
        );

        //需要做一个对前端传来的id的字符串做切割
//        1673986060576,1673976188198,
        String s="1673986060576,1673976188198,";
        String[] split = s.split(",");
        System.out.println(split.length);
        for (String s1:split
             ) {
            Long l=new Long(s1);
            System.out.println(l.getClass());
            System.out.println(l);
        }
    }

    @Autowired
    BlogTagService blogTagService;
    @Test
    @Transactional
    public void testInsertBlogTag(){
        List<BlogTag> blogTags=new ArrayList<>();
        blogTags.add(new BlogTag(1232131311111111l,123123l));
        blogTagService.addBlogTags(blogTags);
        blogTags.add(new BlogTag(123222222222222222l,123333123l));
        blogTagService.addBlogTags(blogTags);

    }

    @Test
    public void testSelectByBlogid(){

        Blog blog = blogService.getBlog(1672993477938l);
        System.out.println(blog);
    }
}
