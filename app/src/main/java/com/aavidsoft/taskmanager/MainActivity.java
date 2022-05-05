package com.aavidsoft.taskmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnAddTask;
    private RecyclerView recyclerTasks;

    private ArrayList<Task> tasks;
    private TasksAdapter tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerTasks = findViewById(R.id.recyclerTasks);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener(new BtnAddTaskClickListener());

        tasks = DBUtil.getInstance(this).getTasks();

        tasksAdapter = new TasksAdapter(tasks);
        recyclerTasks.setAdapter(tasksAdapter);

        recyclerTasks.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
    }

    private class BtnAddTaskClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //start the AddTaskActivity
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            //startActivity(intent);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null) {
            return;
        }

        if(data.getBooleanExtra("status", false) == true) {
            tasks.clear();
            tasks.addAll(DBUtil.getInstance(this).getTasks());
            tasksAdapter.notifyDataSetChanged();
        }

    }
}