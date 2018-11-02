package com.example.dagger_demo;

import javax.inject.Inject;

import dagger.Lazy;

class CoffeeMaker {
  @Inject Lazy<Heater> heater; // Don't want to create a possibly costly heater until we need it.
  @Inject Pump pump;

  @Inject
  public CoffeeMaker(/*Lazy<Heater> heater, Pump pump*/) {
//    this.heater = heater;
//    this.pump = pump;
  }

  public void brew() {
    heater.get().on();
    pump.pump();
    System.out.println(" [_]P coffee! [_]P ");
    heater.get().off();
  }
}
