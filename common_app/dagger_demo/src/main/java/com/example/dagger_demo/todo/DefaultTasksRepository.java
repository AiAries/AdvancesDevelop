package com.example.dagger_demo.todo;


public class DefaultTasksRepository implements TasksRepository {

    @Override
    public String getTask(String id) {
        return "default tasks repository";
    }
}
