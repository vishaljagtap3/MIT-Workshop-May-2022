package com.aavidsoft.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class AddTaskActivity extends AppCompatActivity {

    private EditText edtTaskTitle, edtTaskPriority;
    private ImageView imgTask;
    private Button btnSaveTask;

    private int imageId = R.drawable.bitcode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        btnSaveTask.setOnClickListener(new BtnSaveTaskClickListener());

    }

    private class BtnSaveTaskClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Task newTask = new Task(
                    Math.abs(new Random().nextInt()) % 20,
                    edtTaskTitle.getText().toString(),
                    Integer.parseInt(edtTaskPriority.getText().toString()),
                    imageId
            );

            boolean status = DBUtil.getInstance(AddTaskActivity.this).addTask(newTask);
            if (status) {
                Toast.makeText(AddTaskActivity.this, "Record added!", Toast.LENGTH_LONG)
                        .show();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("status", true);

                setResult(1, resultIntent);

                finish();
            }
            else {
                Toast.makeText(AddTaskActivity.this, "Record can not be added!", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    private void init(){
        setContentView(R.layout.add_task_activity);
        edtTaskTitle = findViewById(R.id.edtTaskTitle);
        edtTaskPriority = findViewById(R.id.edtTaskPriority);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        imgTask = findViewById(R.id.imgTask);

    }
}
