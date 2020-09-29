package com.example.dagger_demo.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.dagger_demo.BaseActivity;
import com.example.dagger_demo.R;
import com.example.dagger_demo.add.AddActivity;
import com.example.dagger_demo.todo.TasksRepository;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    TasksRepository tasksRepository;
    @Inject
    TasksRepository tasksRepository2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.activity_dragger_main, null);
        setContentView(view);
        TextView title = findViewById(R.id.task);
        TextView title2 = findViewById(R.id.task2);
        title.setText(tasksRepository.getTask("0"));
        title2.setText(tasksRepository2.getTask("6"));
//        title.setText(tasksRepository.toString());
//        title2.setText(tasksRepository2.toString());
    }

    public void goAdd(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}
