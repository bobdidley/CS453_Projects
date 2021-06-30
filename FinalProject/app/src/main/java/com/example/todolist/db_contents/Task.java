package com.example.todolist.db_contents;

import java.util.Date;

public class Task {
    private final int user_id;
    private final String name;
    private final String date;   // may need to be a String value
    private String category;   // can this be made an enum instead?
    private int priority;
    private String time;
    public enum STATUS {TODO, DONE}
//    private STATUS status;

    public Task(int user_id, String name, String date, String category, int priority, String time) {
        this.user_id = user_id;
        this.name = name;
        this.date = date;
        this.category = category;
        this.priority = priority;
        this.time = time;
//        this.status = status.DONE;
    }

    public int getUserId() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public int getPriority() {
        return priority;
    }

    public String getTime() {
        return time;
    }
}
