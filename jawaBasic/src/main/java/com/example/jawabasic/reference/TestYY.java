package com.example.jawabasic.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

class TestYY {

//    android.util.LruCache  推荐使用
    //are cleared at the discretion of the garbage
    // * collector in response to memory demand
    private SoftReference<String> stringSoftReference =new SoftReference<String>("ddd");
    WeakReference<String> weakReference;//the garbage collector clear
    //unreachable reachable  finalize
}
