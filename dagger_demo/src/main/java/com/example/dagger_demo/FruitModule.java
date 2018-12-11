package com.example.dagger_demo;

import dagger.Module;
import dagger.Provides;

@Module
public class FruitModule {
    @Provides
    static Pump providePump(Thermosiphon pump) {
        return pump;
    }
}
