package com.aavidsoft.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBUtil {

    private static DBUtil dbUtil = null;
    private static final String DB_NAME = "db_tasks";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    public DBUtil(Context context) {
        SQLiteOpenHelper sqLiteOpenHelper =
                new TaskDBHelper(context, DB_NAME, null, DB_VERSION);
        db = sqLiteOpenHelper.getWritableDatabase();
    }

    public static DBUtil getInstance(Context context) {
        if(dbUtil == null) {
            dbUtil = new DBUtil(context);
        }

        return dbUtil;
    }

    public boolean addTask(Task task) {

        ContentValues values = new ContentValues();
        values.put("id", task.getId());
        values.put("title", task.getTitle());
        values.put("priority", task.getPriority());
        values.put("image_id", task.getImageId());

        long rowNum = db.insert("tasks", null, values);
        if(rowNum >= 0) {
            return true;
        }

        return false;
    }

    public ArrayList<Task> getTasks() {

        /*Cursor c = db.query(
                "tasks",
                new String[] {"id", "title", "prioriy", "image_id"},
                "priority > ? and status = ?",
                new String[] {"5", "true"},
                null,
                null,
                "title asc"
        );*/
        Cursor c = db.query(
                "tasks",
                null,
                null,
                null,
                null,
                null,
                "id desc"
        );

        ArrayList<Task> tasks = new ArrayList<Task>();

        while(c.moveToNext()) {
            tasks.add(
                    new Task(
                            c.getInt(0),
                            c.getString(1),
                            c.getInt(2),
                            c.getInt(3)
                    )
            );
        }

        c.close();

        return tasks;
    }

    class TaskDBHelper extends SQLiteOpenHelper {

        public TaskDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table tasks(id integer primary key, title text, priority integer, image_id integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
