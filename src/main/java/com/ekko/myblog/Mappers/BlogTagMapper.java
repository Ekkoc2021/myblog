package com.ekko.myblog.Mappers;

import com.ekko.myblog.pojo.BlogTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogTagMapper {
    void insertBlogTag(@Param("BlogTag") BlogTag blogTag);

    void deleteByBlogId(@Param("id") Long id);

    void deleteByTagId(@Param("tagid") Long tagid);
}
