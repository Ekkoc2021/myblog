package com.ekko.myblog;

import com.ekko.myblog.Mappers.testMapper;
import com.ekko.myblog.pojo.Type;
import com.ekko.myblog.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TypeServiceTest {

    @Autowired
    testMapper tm;

    @Autowired
    TypeService typeService;

    @Test
    void contextLoads() {
//        List<test> tests = tm.selectAll();
//        System.out.println(tests.get(0));

        PageInfo<Type> typePageInfo = typeService.listPage(1);
        List<Type> list = typePageInfo.getList();
        for (Type t : list) {
            System.out.println(t);
        }
        System.out.println(typePageInfo.getTotal());


    }

    @Test
    public void testInsertType(){
        Type type=new Type();
        type.setId(1233333L);
        type.setName("java零基础!");
        System.out.println(typeService.saveType(type));
    }

    @Test
    public void testGetTypeById(){
        System.out.println(typeService.getType(1233333L));
    }
    @Test
    public void testUpdateType(){
        Type type=new Type();
        type.setName("go基础");
        typeService.updateType(1233333L,type);
    }
    @Test
    public void testDeleteType(){
        typeService.deleteType(1233333L);
    }
}
