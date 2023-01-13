package com.ekko.myblog.service;

import com.ekko.myblog.pojo.Type;

import com.github.pagehelper.PageInfo;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);
    Type getType(Long id);
    PageInfo<Type> listPage(int pageNum);
    Type updateType(Long id,Type type);
    void deleteType(Long id);
    List<Type> listType();
    PageInfo<Type> listPageIndex(int i);
    List<Type> listShowType();
}

