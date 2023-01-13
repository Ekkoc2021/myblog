package com.ekko.myblog.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class Type {
    private Long id;
    private String name;
    private List<Blog> blogs = new ArrayList<>();
    private int blogsize;
}
