package com.example.jawabasic.hash;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class HashMapTest {
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    public static void main(String[] arg){
        HashMap<String,String> hashMap = new HashMap<>();
        ConcurrentHashMap<String,String> hmap = new ConcurrentHashMap<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 15; i++) {
            System.out.println("=============="+i);
            final int finalI = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(finalI%2*1000);
                        System.out.println(Thread.currentThread().getName()+"==="+ finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (i == 6) {
                executor.shutdown();
//                executor.shutdownNow();
//                try {
//                    executor.awaitTermination(2,TimeUnit.SECONDS);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
        System.out.println("end");
    }
}
