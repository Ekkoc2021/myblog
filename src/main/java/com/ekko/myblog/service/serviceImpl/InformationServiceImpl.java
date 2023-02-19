package com.ekko.myblog.service.serviceImpl;

import com.ekko.myblog.Mappers.InformationMapper;
import com.ekko.myblog.pojo.Information;
import com.ekko.myblog.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    InformationMapper informationMapper;
    @Override
    public void increase() {
        informationMapper.increase();
    }

    @Override
    public Information getInformation() {
        List<Information> information = informationMapper.selectInfor();
        if (information.size()>0) {
            return information.get(0);
        }
        return null;
    }
}
