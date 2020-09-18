package com.example.dagger_demo;


import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Component(modules = {MainActivityModule.class,
        AppModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent  {
    void inject(MyApplication application);
}

