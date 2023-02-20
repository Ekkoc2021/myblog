package com.ekko.myblog.service;

import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.Tag;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TagService {
    void setBlogTags(PageInfo<Blog> pageInfo);

    //查所有
    PageInfo<Tag> allTags(int pageNum);

    //根据id查询
    Tag getTags(Long id);

    //根据id删除
    void deleteTags(Long id);

    //根据id修改
    void updateTags(Long id, String name);

    //添加标签
    boolean addTags(Tag tag);

    List<Tag> listTags();

    List<Tag> getTagsByBlogId(Long id);

    PageInfo<Tag> listPageIndex(int i);

    List<Tag> listTagsWithBlogsize();
}
