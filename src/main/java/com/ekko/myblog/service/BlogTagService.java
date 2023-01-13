package com.ekko.myblog.service;

import com.ekko.myblog.pojo.BlogTag;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BlogTagService {
    void addBlogTags(List<BlogTag> blogTags);

    void deleteBlogTag(Long id);
}
