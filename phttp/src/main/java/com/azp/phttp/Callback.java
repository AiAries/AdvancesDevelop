package com.azp.phttp;


public interface Callback<T> {
    void onError(Exception e);

    void onSuccess(T t);
}