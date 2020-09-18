package com.example.dagger_demo;

import com.example.dagger_demo.todo.LocalTasksData;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalTasksModule {

    @Provides
    public LocalTasksData providesLocalTaskData(){
        return new LocalTasksData();
    }

}
