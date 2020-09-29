package com.example.dagger_demo.main;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {})
public interface MainActivitySubComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MainActivity> {

    }
}