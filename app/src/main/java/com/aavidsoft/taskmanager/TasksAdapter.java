package com.aavidsoft.taskmanager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    private ArrayList<Task> tasks;

    class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView taskView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskView = (TextView) itemView;
        }
    }


    public TasksAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TextView txtTask = new TextView(parent.getContext());
        txtTask.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        txtTask.setTextSize(20);
        txtTask.setPadding(10, 10, 10, 10);


        return new TaskViewHolder(txtTask);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.taskView.setText(
                tasks.get(position).getTitle()
        );
    }
}
