package org.lizhishu.love.utils;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: gzip
 * @Date: 2019/12/7  23:53
 * @Description:
 **/
@Component
public class Fun {

    public static boolean equalsNull(Object value) {
        if ((value != null) && (!value.equals("")) && (!value.equals("null"))) {
            return false;
        }
        return true;
    }

    public static boolean equalsListNull(List<?> list) {
        if ((list != null) && (list.size() > 0)) {
            return false;
        }
        return true;
    }
}
