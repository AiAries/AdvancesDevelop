package com.example.jawabasic.rxjava;

class Observable<T> {


    public interface ObservableOnSubscribe<T> {
        void call(Subscriber<T> subscriber);
    }

    public  void  subscribe(Subscriber<T> subscriber) {
//        source.call(subscriber);
    }

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate<T>(source);
    }
}
