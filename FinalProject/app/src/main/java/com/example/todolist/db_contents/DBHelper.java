/**
 * Database class and relevant functions.
 *
 * @file DBHelper.java
 * @authors Fiona Le & James Austin Jr.
 * @date 07/01/2021
 * @version 1.0
 */

package com.example.todolist.db_contents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.todolist.Login;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    /* Users Table */
    public static final String DATABASE_NAME = "DBToDoList.db";
    public static final String USERS_TABLE_NAME = "users";
    public static final String TASKS_TABLE_NAME = "tasks";
    public static final String USERS_COL_ID = "user_id";
    public static final String USERS_COL_USERNAME = "username";
    public static final String USERS_COL_PASSWORD = "password";

    /* Tasks Table */
    public static final String TASKS_COL_ID = "task_id";
    public static final String TASKS_COL_TASK_NAME = "task_name";
    public static final String TASKS_COL_DATE = "date";
    public static final String TASKS_COL_CATEGORY = "category";
    public static final String TASKS_COL_PRIORITY = "priority";
    public static final String TASKS_COL_TIME = "time";
    public static final String TASKS_COL_STATUS = "status";

    String sql;

    /**
     * Overloaded Constructor.
     * @param context Context
     */
    public DBHelper(@Nullable Context context) {   // do we need a version number?
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Creates the database if need be.
     * @param db SQLiteDatabase
     */
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
                "user_id integer)";
        db.execSQL(sql);
    }

    /**
     * Upgrades the database if necessary.
     * @param db SQLiteDatabase
     * @param oldVersion int
     * @param newVersion int
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // users
        sql = "DROP TABLE IF EXISTS " + USERS_TABLE_NAME;
        db.execSQL(sql);

        // tasks
        sql = "DROP TABLE IF EXISTS " + TASKS_TABLE_NAME;
        db.execSQL(sql);
    }

    /**
     * Inserts a new user into the database. Boolean is to determine whether insertion was successful or not.
     * @param user User
     * @return boolean
     */
    public boolean insertUser(User user) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            // check if username already exists
            if(getAllUsers().containsKey(user.getUsername())) {
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

    /**
     * Checks if the credentials exist in the database.
     * @param username String
     * @param password String
     * @return boolean
     */
    public boolean isExistingUser(String username, String password) {
        HashMap<String, String> users = getAllUsers();
        return users.containsKey(username) && users.get(username).equals(password);
    }

    /**
     * Updates a user's password in the database. Boolean is to determine whether update was successful or not.
     * @param newPassword String
     * @param user_id int
     * @return boolean
     */
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

    /**
     * Updates a user's task in the database. Boolean is to determine whether update was successful or not.
     * Sets the task status to be DONE.
     * @param task_id int
     * @param user_id int
     * @return boolean
     */
    public boolean updateUserTask(int task_id, int user_id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            sql = "UPDATE " + TASKS_TABLE_NAME + " SET " + TASKS_COL_STATUS + " = \"" + Task.STATUS.DONE.name() +
                    "\" WHERE " + USERS_COL_ID + " = " + user_id + " AND " + TASKS_COL_ID + " = " + task_id;
            db.execSQL(sql);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Inserts a new task into the database. Boolean is to determine whether insertion was successful or not.
     * @param task Task
     * @return boolean
     */
    public boolean insertTask(Task task) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(TASKS_COL_TASK_NAME, task.getName());
            values.put(TASKS_COL_DATE, task.getDate());
            values.put(TASKS_COL_CATEGORY, task.getCategory());
            values.put(TASKS_COL_PRIORITY, task.getPriority());
            values.put(TASKS_COL_TIME, task.getTime());
            values.put(TASKS_COL_STATUS, task.getStatus());
            values.put(USERS_COL_ID, task.getUserId());

            db.insert(TASKS_TABLE_NAME, null, values);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Returns the task list based on the user id query.
     * @param user_id int
     * @return ArrayList<HashMap<String, String>>
     */
    public ArrayList<HashMap<String, String>> getUserTasks(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "SELECT * FROM " + TASKS_TABLE_NAME + " WHERE " + USERS_COL_ID + " = " + user_id +
                " AND NOT " + TASKS_COL_STATUS + " = \"" + Task.STATUS.DONE.name() + "\"";   // may not work for user_id column name
        Cursor curs = db.rawQuery(sql, null);

        ArrayList<HashMap<String, String>>  taskList = new ArrayList<>();
        String[] columnNames = curs.getColumnNames();

        curs.moveToFirst();
        while(!curs.isAfterLast()) {
            HashMap<String, String> individualTask = new HashMap<>();
            for(String col_name : columnNames) {
                individualTask.put(col_name, curs.getString(curs.getColumnIndex(col_name)));
            }
            taskList.add(individualTask);
            curs.moveToNext();
        }

        return taskList;
    }

    /**
     * Returns the task list based on the category query.
     * @param user_id int
     * @param category String
     * @return ArrayList<HashMap<String, String>>
     */
    public ArrayList<HashMap<String, String>> getCategoryTasks(int user_id, String category) {
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "SELECT * FROM " + TASKS_TABLE_NAME + " WHERE " + USERS_COL_ID + " = " + user_id +
                " AND " + TASKS_COL_CATEGORY + " = \"" + category + "\"" +
                " AND NOT " + TASKS_COL_STATUS + " = \"" + Task.STATUS.DONE.name() + "\"";;   // may not work for user_id column name
        Cursor curs = db.rawQuery(sql, null);

        ArrayList<HashMap<String, String>>  taskList = new ArrayList<>();
        String[] columnNames = curs.getColumnNames();

        curs.moveToFirst();
        while(!curs.isAfterLast()) {
            HashMap<String, String> individualTask = new HashMap<>();
            for(String col_name : columnNames) {
                individualTask.put(col_name, curs.getString(curs.getColumnIndex(col_name)));
            }
            taskList.add(individualTask);
            curs.moveToNext();
        }

        return taskList;
    }

    /**
     * Returns the task list based on the priority query.
     * @param user_id int
     * @param priority int
     * @return ArrayList<HashMap<String, String>>
     */
    public ArrayList<HashMap<String, String>> getPriorityTasks(int user_id, int priority) {
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "SELECT * FROM " + TASKS_TABLE_NAME + " WHERE " + USERS_COL_ID + " = " + user_id +
                " AND " + TASKS_COL_PRIORITY + " = " + priority +
                " AND NOT " + TASKS_COL_STATUS + " = \"" + Task.STATUS.DONE.name() + "\"";   // may not work for user_id column name
        Cursor curs = db.rawQuery(sql, null);

        ArrayList<HashMap<String, String>>  taskList = new ArrayList<>();
        String[] columnNames = curs.getColumnNames();

        curs.moveToFirst();
        while(!curs.isAfterLast()) {
            HashMap<String, String> individualTask = new HashMap<>();
            for(String col_name : columnNames) {
                individualTask.put(col_name, curs.getString(curs.getColumnIndex(col_name)));
            }
            taskList.add(individualTask);
            curs.moveToNext();
        }

        return taskList;
    }

    /**
     * Returns the task list based on the date query.
     * @param user_id int
     * @param date String
     * @return ArrayList<HashMap<String, String>>
     */
    public ArrayList<HashMap<String, String>> getDateTasks(int user_id, String date) {
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "SELECT * FROM " + TASKS_TABLE_NAME + " WHERE " + USERS_COL_ID + " = " + user_id +
                " AND " + TASKS_COL_DATE + " = \"" + date +
                "\" AND NOT " + TASKS_COL_STATUS + " = \"" + Task.STATUS.DONE.name() + "\"";   // may not work for user_id column name
        Cursor curs = db.rawQuery(sql, null);

        ArrayList<HashMap<String, String>>  taskList = new ArrayList<>();
        String[] columnNames = curs.getColumnNames();

        curs.moveToFirst();
        while(!curs.isAfterLast()) {
            HashMap<String, String> individualTask = new HashMap<>();
            for(String col_name : columnNames) {
                individualTask.put(col_name, curs.getString(curs.getColumnIndex(col_name)));
            }
            taskList.add(individualTask);
            curs.moveToNext();
        }

        return taskList;
    }

    /**
     * Returns the user id associated to the username. Usernames must be unique.
     * @param username String
     * @return int
     */
    public int getUserId(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "SELECT " + USERS_COL_ID + ", " + USERS_COL_USERNAME + " FROM " + USERS_TABLE_NAME;
        Cursor curs = db.rawQuery(sql, null);

        curs.moveToFirst();
        while(!curs.isAfterLast()) {
            if(curs.getString(curs.getColumnIndex(USERS_COL_USERNAME)).equals(username)) {
                return Integer.parseInt(curs.getString(curs.getColumnIndex(USERS_COL_ID)));
            }
            curs.moveToNext();
        }
        return -1;
    }

    /**
     * Return the logged in user's username.
     * @return String
     */
    public String getUsername() {
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "SELECT " + USERS_COL_ID + ", " + USERS_COL_USERNAME + " FROM " + USERS_TABLE_NAME +
                " WHERE " + USERS_COL_ID + " = " + Login.USER_ID;
        Cursor curs = db.rawQuery(sql, null);

        curs.moveToFirst();
        if(!curs.isAfterLast())
            return curs.getString(curs.getColumnIndex(USERS_COL_USERNAME));
        return null;
    }

    /**
     * Returns all the users and passwords.
     * @return HashMap<String, String>
     */
    private HashMap<String, String> getAllUsers() {
//        ArrayList<String> users_array = new ArrayList<>();
        HashMap<String, String> users_credentials = new HashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "SELECT * FROM " + USERS_TABLE_NAME;
        Cursor curs = db.rawQuery(sql, null);

        curs.moveToFirst();
        while(!curs.isAfterLast()) {
            users_credentials.put(curs.getString(curs.getColumnIndex(USERS_COL_USERNAME)),
                    curs.getString(curs.getColumnIndex(USERS_COL_PASSWORD)));
            curs.moveToNext();
        }

        return users_credentials;
    }
}
