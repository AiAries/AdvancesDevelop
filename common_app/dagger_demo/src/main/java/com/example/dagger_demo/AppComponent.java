package com.example.dagger_demo;


import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {MainActivityModule.class,
        AppModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent  {
    void inject(MyApplication application);
}

