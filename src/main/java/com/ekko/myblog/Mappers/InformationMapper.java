package com.ekko.myblog.Mappers;

import com.ekko.myblog.pojo.Information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InformationMapper {
    void increase();
    List<Information> selectInfor();
}
