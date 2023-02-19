package com.ekko.myblog;

import com.ekko.myblog.pojo.Information;
import com.ekko.myblog.service.InformationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InformationServiceTest {
    @Autowired
    InformationService informationService;
    @Test
    public void test(){
        informationService.increase();
        Information information = informationService.getInformation();
        System.out.println(information);
    }
}
