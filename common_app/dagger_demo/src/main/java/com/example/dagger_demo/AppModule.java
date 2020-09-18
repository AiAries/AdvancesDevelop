package com.example.dagger_demo;

import com.example.dagger_demo.todo.DefaultTasksRepository;
import com.example.dagger_demo.todo.TasksRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public static TasksRepository providesTasksRepository() {
        return new DefaultTasksRepository();
    }
}
