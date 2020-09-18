package com.example.dagger_demo;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {LocalTasksModule.class})
public interface MainActivitySubComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    public interface Factory extends AndroidInjector.Factory<MainActivity> {}

}