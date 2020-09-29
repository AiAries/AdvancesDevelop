package com.example.dagger_demo.todo;

public class LocalTasksData implements TaskData{

    @Override
    public String getTask(String id) {
        if ("0".equals(id)) {
            return "0-  local";
        }
        return null;
    }
}
