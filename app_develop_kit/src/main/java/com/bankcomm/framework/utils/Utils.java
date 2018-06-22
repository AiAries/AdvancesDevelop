package com.bankcomm.framework.utils;

/**
 * Created by A170860 on 2018/6/22.
 */

public class Utils {
    private Utils() {

    }

    public static <T> T checkNull(Class<T> t) {
        if (t==null) {
            throw new NullPointerException(t.getName()+"is null");
        }
        return (T) t;
    }
}
