package com.ekko.myblog.Mappers;

import com.ekko.myblog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> selectAllTag();

    int insertTags(@Param("tag") Tag tag);

    void updateTag(@Param("id") Long id, @Param("name") String name);

    void deleteTags(@Param("id") Long id);

    List<Tag> selectTabsByBlogId(@Param("blogid") Long id);

    List<Tag> selectTagAndBlogSize();

}
