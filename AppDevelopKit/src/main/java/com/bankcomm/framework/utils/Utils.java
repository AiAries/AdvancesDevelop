package com.bankcomm.framework.utils;

import android.support.annotation.Nullable;

/**
 * Created by A170860 on 2018/6/22.
 */

public class Utils {
    private Utils() {

    }

    public static <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
