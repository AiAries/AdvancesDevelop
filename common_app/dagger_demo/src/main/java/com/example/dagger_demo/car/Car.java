package com.example.dagger_demo.car;

import javax.inject.Inject;
import javax.inject.Named;

public class Car {
    @Inject
    @Named("driver")
    Seat driverSeat;

    @Inject
    @Named("passenger")
    Seat passengerSeat;

    public Car() {
      DaggerCarComponent.builder().carModule(new CarModule()).build().inject(this);
    }

    public Car(String blue) {
        System.out.println("new car and its color is" + blue);
    }

    public static void main(String[] args) {
        Car car = new Car();
    }
}


