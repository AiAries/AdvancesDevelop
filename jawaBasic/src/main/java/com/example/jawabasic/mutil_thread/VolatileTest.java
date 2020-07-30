package com.example.jawabasic.mutil_thread;

class VolatileTest {
    public static void main(String[] arg) {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    for (int j = 0; j < 100; j++) {
                        test.increase();
                    }
                }
            }.start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(test.num);
    }
    public  volatile int num;
    public  void increase(){
        num++;
    }
}
