package com.example.dagger_demo;

import com.example.dagger_demo.todo.DefaultTasksRepository;
import com.example.dagger_demo.todo.TasksRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    public static TasksRepository providesTasksRepository() {
        return new DefaultTasksRepository();
    }
}
