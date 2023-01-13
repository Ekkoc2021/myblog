package com.ekko.myblog.service.serviceImpl;

import com.ekko.myblog.Mappers.TagMapper;
import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.Tag;
import com.ekko.myblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagService {
    @Override
    public void setBlogTags(PageInfo<Blog> pageInfo) {
        for (Blog b:pageInfo.getList()) {
            b.setTags(tagMapper.selectTabsByBlogId(b.getId()));
        }
    }

    @Override
    public List<Tag> listTagsWithBlogsize() {
        return tagMapper.selectTagAndBlogSize();
    }

    @Override
    public PageInfo<Tag> listPageIndex(int i) {
        PageHelper.startPage(i,10);
        List<Tag> tags = tagMapper.selectTagAndBlogSize();
        PageInfo<Tag> pageInfo=new PageInfo<>(tags);
        return pageInfo;
    }

    @Override
    public List<Tag> getTagsByBlogId(Long id) {
        List<Tag> tags=tagMapper.selectTabsByBlogId(id);
        return tags;
    }

    @Override
    public List<Tag> listTags() {
        return tagMapper.selectAllTag();
    }

    @Autowired
    TagMapper tagMapper;
    @Override
    public PageInfo<Tag> allTags(int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Tag> tags = tagMapper.selectAllTag();
        PageInfo<Tag> pageInfo=new PageInfo<>(tags);
        return pageInfo;
    }

    @Override
    public Tag getTags(Long id) {
        return null;
    }

    @Override
    public void deleteTags(Long id) {
        tagMapper.deleteTags(id);
    }

    @Override
    public void updateTags(Long id, String name) {
        tagMapper.updateTag(id,name);
    }

    @Override
    public boolean addTags(Tag tag) {
        if (tagMapper.insertTags(tag)>0){
            return true;
        }
        return false;
    }
}
