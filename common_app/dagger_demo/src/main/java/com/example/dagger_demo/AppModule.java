package com.example.dagger_demo;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.dagger_demo.todo.LocalTaskDataQ;
import com.example.dagger_demo.todo.LocalTasksData;
import com.example.dagger_demo.todo.RemoteTaskDataQ;
import com.example.dagger_demo.todo.RemoteTasksData;
import com.example.dagger_demo.todo.TaskData;
import com.example.dagger_demo.todo.TasksRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context appContext;

    public AppModule(Context context) {
        this.appContext = context;
    }

    @Provides
    public LayoutInflater provideLayoutInflater(){
        return (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Singleton
    @Provides
    public  TasksRepository providesTasksRepository(@LocalTaskDataQ TaskData localTask,
                                                    @RemoteTaskDataQ TaskData remoteTask) {
        return new TasksRepository(localTask,remoteTask);
    }
    //提供返回类型相同的解决方式  --加别名
    @Provides
    @LocalTaskDataQ
    public TaskData providesLocalTaskData(){
        return new LocalTasksData();
    }
    @RemoteTaskDataQ
    @Provides
    public TaskData providesRemoteTaskData(){
        return new RemoteTasksData();
    }

}
