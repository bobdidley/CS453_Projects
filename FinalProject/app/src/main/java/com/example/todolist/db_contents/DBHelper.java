package com.example.todolist.db_contents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DBToDoList.db";
    public static final String USERS_TABLE_NAME = "users";
    public static final String TASKS_TABLE_NAME = "tasks";
    public static final String USERS_COL_ID = "user_id";
    public static final String USERS_COL_USERNAME = "username";
    public static final String USERS_COL_PASSWORD = "password";

    public static final String TASKS_COL_ID = "task_id";
    public static final String TASKS_COL_TASK_NAME = "task_name";
    public static final String TASKS_COL_DATE = "date";
    public static final String TASKS_COL_CATEGORY = "category";
    public static final String TASKS_COL_PRIORITY = "priority";
    public static final String TASKS_COL_TIME = "time";
    public static final String TASKS_COL_STATUS = "status";

    String sql;

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // users
        sql = "CREATE TABLE " + USERS_TABLE_NAME +
                "(user_id integer PRIMARY KEY AUTOINCREMENT, " +
                "username text, " +
                "password text)";
        db.execSQL(sql);

        // tasks
        sql = "CREATE TABLE " + TASKS_TABLE_NAME +
                "(task_id integer PRIMARY KEY AUTOINCREMENT, " +
                "task_name text, " +
                "date text, " +
                "category text, " +
                "priority integer, " +
                "time text, " +
                "status text, " +
                "FOREIGN KEY (user_id)\n" +
                "       REFERENCES " + USERS_TABLE_NAME + " (user_id))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // users
        sql = "DROP TABLE IF EXISTS " + USERS_TABLE_NAME;
        db.execSQL(sql);

        // tasks
        sql = "DROP TABLE IF EXISTS " + TASKS_TABLE_NAME;
        db.execSQL(sql);
    }

    public boolean insertUser(User user) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            // check if username already exists
            if(getAllUsers().contains(user.getUsername())) {
                throw new Exception("User already exists");
            }

            values.put(USERS_COL_USERNAME, user.getUsername());
            values.put(USERS_COL_PASSWORD, user.getPassword());

            db.insert(USERS_TABLE_NAME, null, values);   // does this cause error if i don't include id?
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean updateUserPassword(String newPassword, int user_id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            sql = "UPDATE " + USERS_TABLE_NAME + " SET " + USERS_COL_PASSWORD + " = " + newPassword +
                    " WHERE " + USERS_COL_ID + " = " + user_id;
            db.execSQL(sql);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean insertTask(Task task) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(TASKS_COL_TASK_NAME, task.getName());
            values.put(TASKS_COL_DATE, task.getDate());
            values.put(TASKS_COL_CATEGORY, task.getCategory());
            values.put(TASKS_COL_PRIORITY, task.getPriority());
            values.put(TASKS_COL_TIME, task.getTime());
            values.put(TASKS_COL_STATUS, String.valueOf(Task.STATUS.TODO));
            values.put(USERS_COL_ID, task.getUserId());

            db.insert(TASKS_TABLE_NAME, null, values);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Cursor getUserTasks(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "SELECT * FROM " + TASKS_TABLE_NAME + " WHERE " + USERS_COL_ID + " = " + user_id;   // may not work for user_id column name
        Cursor curs = db.rawQuery(sql, null);

        return curs;
    }

    private ArrayList<String> getAllUsers() {
        ArrayList<String> users_array = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "SELECT * FROM " + USERS_TABLE_NAME;
        Cursor curs = db.rawQuery(sql, null);

        curs.moveToFirst();
        while(!curs.isAfterLast()) {
            users_array.add(curs.getString(curs.getColumnIndex(USERS_COL_USERNAME)));
            curs.moveToNext();
        }

        return users_array;
    }

}
