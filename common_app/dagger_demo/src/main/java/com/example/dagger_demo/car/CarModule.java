package com.example.dagger_demo.car;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class CarModule{
//    @Provides
//    public Car providesCar(){
//        return  new Car();
//    }
    @Named("driver")
    @Provides
    public Seat providesDriverSeat(){
        return  new Seat("driver");
    }
    @Named("passenger")
    @Provides
    public Seat providesPassengerSeat(){
        return  new Seat("passenger");
    }
}
