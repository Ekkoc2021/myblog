package com.ekko.myblog.pojo;

import lombok.Data;

@Data
public class BlogQuery {
    private String title;
    private String typeName;
    private boolean recommend;
    private Long userid;
    private int page;
    public void reSet(){
        if ("".equals(typeName)){
            typeName=null;
        }
        if ("".equals(title)){
            title=null;
        }
    }
}
