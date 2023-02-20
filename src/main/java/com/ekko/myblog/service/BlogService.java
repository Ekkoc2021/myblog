package com.ekko.myblog.service;

import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.BlogQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlogAndConvert(Long id);

    Blog getPublishedBlogAndConvert(Long id);

    PageInfo<Blog> listPulbishedBlog(int pageNum);

    PageInfo<Blog> getBlogs(BlogQuery blogQuery);

    void addBlogs(Blog blog);

    Blog getBlog(Long id);

    void editBlog(Blog blog);

    void deleteBlog(Long id);

    PageInfo<Blog> listRecom(int i);

    PageInfo<Blog> searchBlog(String search, int pagenum);

    void viewAdd(Long id);

    PageInfo<Blog> listBlogbByTypeId(Long id, int pagenum);

    PageInfo<Blog> listBlogByTagId(Long id, int pagenum);

    PageInfo<Blog> getArchivesDetail(int num, String year);

    PageInfo<String> getArchivesYear(int pageNum);

    int getBlogsCount();

    void updateBlogsTypename(String oldname, String newname);

    PageInfo<Blog> getDiarys(int pageNum);
}
