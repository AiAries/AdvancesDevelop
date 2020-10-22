package com.example.jawabasic.rxjava;

class AsyncOperate {
    public static void main(String[] arg) {
        System.out.println("start");
         Observable.create(
                new Observable.ObservableOnSubscribe<Integer>(){
                    @Override
                    public void call(Subscriber<Integer> subscriber) {
                        subscriber.onNext(66);
                    }
                }
        ).subscribe(
                new Subscriber<Integer>(){
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }
                }
        );

    }
}
