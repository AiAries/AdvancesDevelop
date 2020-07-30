package com.example.jawabasic.rxjava;

interface Subscriber<T> {
    void onCompleted();
    void onError();
    void onNext(T t);
}
