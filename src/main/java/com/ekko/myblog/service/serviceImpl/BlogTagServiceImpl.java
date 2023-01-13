package com.ekko.myblog.service.serviceImpl;

import com.ekko.myblog.Mappers.BlogTagMapper;
import com.ekko.myblog.pojo.BlogTag;
import com.ekko.myblog.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Override
    public void addBlogTags(List<BlogTag> blogTags) {
        for (BlogTag blogTag: blogTags) {
            blogTagMapper.insertBlogTag(blogTag);
        }
    }

    @Override
    public void deleteBlogTag(Long id) {
        blogTagMapper.deleteByBlogId(id);
    }
}
