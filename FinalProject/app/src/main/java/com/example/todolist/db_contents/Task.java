package com.example.todolist.db_contents;

public class Task {
    private final int user_id;
    private final String name;
    private final String date;
    private String category;
    private int priority;
    private String time;
    public enum STATUS {TODO, DONE}
    private String status;

    /**
     * Overloaded Constructor
     * @param user_id int
     * @param name String
     * @param date String
     * @param category String
     * @param priority int
     * @param time String
     * @param status STATUS
     */
    public Task(int user_id, String name, String date, String category, int priority, String time, STATUS status) {
        this.user_id = user_id;
        this.name = name;
        this.date = date;
        this.category = category;
        this.priority = priority;
        this.time = time;
        this.status = status.name();
    }

    /**
     * Return the user id.
     * @return int
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * Return the name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Return the date.
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * Return the category.
     * @return String
     */
    public String getCategory() {
        return category;
    }

    /**
     * Return the priority.
     * @return int
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Return the time.
     * @return String
     */
    public String getTime() {
        return time;
    }

    /**
     * Return the status.
     * @return String
     */
    public String getStatus() { return status; }
}
