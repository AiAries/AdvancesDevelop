package com.example.dagger_demo;


import com.example.dagger_demo.add.AddActivityModule;
import com.example.dagger_demo.main.MainActivityModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
        MainActivityModule.class, AddActivityModule.class,
        AppModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent  {
    void inject(MyApplication application);
}

