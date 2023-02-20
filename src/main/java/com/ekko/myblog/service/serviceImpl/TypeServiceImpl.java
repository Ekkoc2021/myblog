package com.ekko.myblog.service.serviceImpl;

import com.ekko.myblog.Mappers.TypeMapper;
import com.ekko.myblog.pojo.Type;
import com.ekko.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Override
    public List<Type> listShowType() {
        List<Type> types = typeMapper.getTypeAndSize();
        return types;
    }

    @Override
    public PageInfo<Type> listPageIndex(int pageNum) {
        PageHelper.startPage(pageNum, 6);
        //直接查询所有数据:实际sql已经被处理
        List<Type> types = typeMapper.getTypeAndSize();
        //进行包装后:获取所有的分页相关的信息
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        return pageInfo;
    }

    @Override
    public List<Type> listType() {
        return typeMapper.allTypes();
    }

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Type saveType(Type type) {
        if (typeMapper.insertType(type) > 0) {
            return type;
        }
        return null;
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getTypeById(id);

    }

    @Override
    public PageInfo<Type> listPage(int pageNum) {
        return getPages(pageNum, 10);
    }

    public PageInfo<Type> getPages(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //直接查询所有数据:实际sql已经被处理
        List<Type> types = typeMapper.allTypes();
        //进行包装后:获取所有的分页相关的信息
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        return pageInfo;
    }

    @Override
    public Type updateType(Long id, Type type) {
        typeMapper.updateTypeName(id, type.getName());
        return type;
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteTypeById(id);
    }
}
