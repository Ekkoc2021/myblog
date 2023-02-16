package com.ekko.myblog.Mappers;

import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.BlogQuery;
import com.ekko.myblog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {

    List<Blog> selectAllBlogs(@Param("BlogQuery") BlogQuery blogQuery);

    void insertBlog(@Param("blog") Blog blog);

    Blog selectPublishedBlogByid(@Param("id") Long id);

    Blog selectBlogByid(@Param("id") Long id);

    void updateBlog(@Param("blog") Blog blog);

    void deleteBlogById(@Param("id") Long id);

    List<Blog> selectPulbishedBlogs();

    List<Blog> selectRecom();

    List<Blog> selectBlogBySearch(@Param("search") String search);

    void updateBlogView(@Param("id") Long id);

    List<Blog> selectBlogByTypeId(@Param("id") Long id);

    List<Blog> selectBlogByTagId(@Param("tagid") Long id);

    List<String> selectBlogYears();

    List<Blog> selectBlogByYear(@Param("year") String y);

    int selectBlogCount();

    void updateBlogsTypename(@Param("oldname") String oldname, @Param("newname")String newname);

    List<Blog> selectDiary();
}
