package com.example.dagger_demo.todo;

public class RemoteTasksData implements TaskData {
    @Override
    public String getTask(String id) {
        return "remote task";
    }
}
