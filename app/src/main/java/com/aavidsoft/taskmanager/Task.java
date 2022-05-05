package com.aavidsoft.taskmanager;

public class Task {
    private int id;
    private String title;
    private int priority;
    private int imageId;

    public Task(int id, String title, int priority, int imageId) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
