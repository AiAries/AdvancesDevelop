package com.example.dagger_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.dagger_demo.todo.LocalTasksData;
import com.example.dagger_demo.todo.TasksRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {


    @Inject
    LocalTasksData localTasksData;
    @Inject
    TasksRepository tasksRepository;
    @Inject
    TasksRepository tasksRepository2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragger_main);
        TextView title = findViewById(R.id.task);
        TextView title2 = findViewById(R.id.task2);
        title.setText(tasksRepository.getTask("2"));
        title.setText(tasksRepository.toString());
        title2.setText(tasksRepository2.toString());
    }

}
