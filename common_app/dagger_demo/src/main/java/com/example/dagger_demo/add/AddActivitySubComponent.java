package com.example.dagger_demo.add;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface AddActivitySubComponent extends AndroidInjector<AddActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<AddActivity> {}

}