package com.ekko.myblog.Mappers;

import com.ekko.myblog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByUsernameAndUser(@Param("username") String username, @Param("password") String password);

    User selectUserById(@Param("userid") Long userId);
}
