package com.example.jawabasic.memory;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class OnTrimMemoryTest {
    private  WeakReference<String> stringSoftReference;
    public static void main(String[] args) throws InterruptedException {
        String s = "33";
        OnTrimMemoryTest trimMemoryTest= new OnTrimMemoryTest();
        trimMemoryTest.stringSoftReference = new WeakReference<>(s);
        System.gc();
        System.out.println(trimMemoryTest.stringSoftReference.get());
        trimMemoryTest.stringSoftReference = null;
        System.gc();
    }
}
