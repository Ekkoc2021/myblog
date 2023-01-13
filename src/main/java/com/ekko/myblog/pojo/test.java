package com.ekko.myblog.pojo;

import javafx.util.converter.TimeStringConverter;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @data 2023/1/2
 * @breief 用于测试mybatis环境搭建
 */
@Data
public class test {
    private String k;
    private String v;
    private Timestamp date;
}
