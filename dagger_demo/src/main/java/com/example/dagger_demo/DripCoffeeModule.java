package com.example.dagger_demo;

import dagger.Module;
import dagger.Provides;

@Module
public class DripCoffeeModule {
    @Provides static Heater provideHeater() {
        return new ElectricHeater();
    }

    /*@Provides static Pump providePump(Thermosiphon pump) {
        return pump;
    }*/
}
