package com.ekko.myblog.service;

import com.ekko.myblog.pojo.Information;
import org.springframework.stereotype.Service;


public interface InformationService {
    void increase();
    Information getInformation();
}
