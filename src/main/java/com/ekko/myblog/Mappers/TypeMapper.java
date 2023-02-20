package com.ekko.myblog.Mappers;

import com.ekko.myblog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeMapper {
    List<Type> allTypes();

    int insertType(@Param("type") Type type);

    Type getTypeById(@Param("id") Long id);

    void updateTypeName(@Param("id") Long id, @Param("name") String name);

    void deleteTypeById(@Param("id") Long id);

    List<Type> getTypeAndSize();
}
