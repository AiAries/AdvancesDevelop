package com.example.jawabasic.rxjava;

class Observable<T> {

    public interface OnSubscribe<T> {
        void call(Subscriber<T> subscriber);
    }

    public  void  subscribe(Subscriber<T> subscriber) {

    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<T>();
    }
}
