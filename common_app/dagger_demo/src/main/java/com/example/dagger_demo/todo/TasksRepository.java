package com.example.dagger_demo.todo;

public class TasksRepository  implements TaskData{


    private TaskData localTask;
    private TaskData remoteTask;

    public TasksRepository(TaskData localTask, TaskData remoteTask) {
        this.localTask = localTask;
        this.remoteTask = remoteTask;
    }

    @Override
    public String getTask(String id) {
        String task = localTask.getTask(id);
        if (task==null) {
            return remoteTask.getTask(id);
        }
        return task;
    }
}
