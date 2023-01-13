package com.ekko.myblog.Mappers;

import com.ekko.myblog.pojo.test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface testMapper {
    List<test> selectAll();
}
