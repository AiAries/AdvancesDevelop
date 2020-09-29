package com.example.dagger_demo.add;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = AddActivitySubComponent.class)
public abstract class AddActivityModule {
    @Binds
    @IntoMap
    @ClassKey(AddActivity.class)
    abstract AndroidInjector.Factory<?>
    bindYourAndroidInjectorFactory(AddActivitySubComponent.Factory factory);
}
//@ActivityScope
//    @ContributesAndroidInjector(modules = { /* modules to install into the subcomponent */ })
//    abstract MainActivity contributeYourAndroidInjector();