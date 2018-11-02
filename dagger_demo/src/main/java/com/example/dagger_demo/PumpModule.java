package com.example.dagger_demo;

import dagger.Module;
import dagger.Provides;

@Module
class PumpModule {
  @Provides Pump providePump(Thermosiphon pump) {
    return pump;
  }
}
