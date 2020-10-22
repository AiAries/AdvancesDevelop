package com.example.jawabasic.rxjava;

class ObservableCreate<T> extends Observable<T> {

    private ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {

        this.source = source;
    }
}
