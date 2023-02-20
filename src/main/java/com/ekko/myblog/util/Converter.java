package com.ekko.myblog.util;

import com.ekko.myblog.pojo.Tag;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于将前端传输过来的数据转换为特定格式的工具类
 */
public class Converter {
    /**
     * @param ids
     * @return
     */
    public static List<Long> TagsIdCon(String ids) {
        List<Long> Ids = new ArrayList<>();
        String[] split = ids.split(",");
        for (String s : split) {
            Long aLong = new Long(s);
            Ids.add(aLong);
        }
        return Ids;
    }

    /**
     * @param tags
     * @return
     */
    public static String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return "";
        }
    }
}
