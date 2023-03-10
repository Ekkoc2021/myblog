package com.ekko.myblog.service.serviceImpl;

import com.ekko.myblog.Mappers.BlogMapper;
import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.BlogQuery;
import com.ekko.myblog.pojo.Tag;
import com.ekko.myblog.service.BlogService;
import com.ekko.myblog.util.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Override
    public PageInfo<Blog> getDiarys(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Blog> blogList = blogMapper.selectDiary();
        return new PageInfo<>(blogList);
    }

    @Override
    public void updateBlogsTypename(String oldname, String newname) {
        blogMapper.updateBlogsTypename(oldname, newname);
    }

    @Override
    public int getBlogsCount() {
        return blogMapper.selectBlogCount();
    }

    /**
     * @return 归档的年份
     */
    @Override
    public PageInfo<String> getArchivesYear(int pageNum) {
        PageHelper.startPage(pageNum, 11);
        List<String> years = blogMapper.selectBlogYears();
        PageInfo<String> pageInfo = new PageInfo(years);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> getArchivesDetail(int num, String year) {
        PageHelper.startPage(num, 11);
        List<Blog> blogList = blogMapper.selectBlogByYear(year);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> listBlogByTagId(Long id, int pagenum) {
        PageHelper.startPage(pagenum, 5);
        List<Blog> blogs = blogMapper.selectBlogByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> listBlogbByTypeId(Long id, int pagenum) {
        PageHelper.startPage(pagenum, 5);
        List<Blog> blogList = blogMapper.selectBlogByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        return pageInfo;
    }

    @Override
    public void viewAdd(Long id) {
        blogMapper.updateBlogView(id);
    }

    @Override
    public Blog getPublishedBlogAndConvert(Long id) {
        Blog blog = blogMapper.selectPublishedBlogByid(id);
        if (blog == null) {
            return null;
        }
        String s = MarkdownUtils.markdownToHtmlExtensions(blog.getContent());
        blog.setContent(s);
        return blog;
    }

    @Override
    public Blog getBlogAndConvert(Long id) {
        Blog blog = blogMapper.selectBlogByid(id);
        if (blog == null) {
            return null;
        }
        String s = MarkdownUtils.markdownToHtmlExtensions(blog.getContent());
        blog.setContent(s);
        return blog;
    }

    @Override
    public PageInfo<Blog> searchBlog(String search, int pagenum) {
        PageHelper.startPage(pagenum, 5);
        List<Blog> blogList = blogMapper.selectBlogBySearch(search);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogList);
        return blogPageInfo;
    }

    @Override
    public PageInfo<Blog> listRecom(int i) {
        PageHelper.startPage(i, 10);
        List<Blog> blogs = blogMapper.selectRecom();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> listPulbishedBlog(int pageNum) {
        PageHelper.startPage(pageNum, 7);
        List<Blog> blogList = blogMapper.selectPulbishedBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        return pageInfo;
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlogById(id);
    }

    @Override
    public void editBlog(Blog blog) {
        blogMapper.updateBlog(blog);
    }

    @Override
    public void addBlogs(Blog blog) {
        blogMapper.insertBlog(blog);
    }

    @Override
    public Blog getBlog(Long id) {

        return blogMapper.selectBlogByid(id);
    }

    @Autowired
    BlogMapper blogMapper;

    @Override
    public PageInfo<Blog> getBlogs(BlogQuery blogQuery) {
        PageHelper.startPage(blogQuery.getPage(), 10);
        List<Blog> list = blogMapper.selectAllBlogs(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
