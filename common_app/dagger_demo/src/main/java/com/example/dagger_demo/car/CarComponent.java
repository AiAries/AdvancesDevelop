package com.example.dagger_demo.car;

import dagger.Component;

@Component(modules = {CarModule.class})
public interface  CarComponent{
    void inject(Car car);
}