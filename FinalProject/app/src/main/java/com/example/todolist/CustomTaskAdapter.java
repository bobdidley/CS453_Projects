/**
 *
 *
 * @file CustomTaskAdapter.java
 * @authors Fiona Le & James Austin Jr.
 * @date 07/01/2021
 * @version 1.0
 */

package com.example.todolist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.db_contents.DBHelper;
import com.example.todolist.ui.gallery.TaskFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;


public class CustomTaskAdapter extends RecyclerView.Adapter<CustomTaskAdapter.TaskViewHolder> {

    ArrayList<HashMap<String,String>> taskList;
    private LayoutInflater inflater;

    /**
     * Overloaded Constructor
     * @param context Context
     */
    public CustomTaskAdapter(Context context, ArrayList<HashMap<String, String>> taskList){
        this.inflater = LayoutInflater.from(context);
        this.taskList = taskList;
    }

    /**
     * Clears the task list array and updates so a new task list can be put in.
     */
    public void reset() {
        this.taskList.clear();
        notifyDataSetChanged();
    }

    /**
     * Sets the new task list.
     * @param taskList ArrayList<HashMap<String, String>>
     */
    public void setTasksList(ArrayList<HashMap<String,String>> taskList){
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    /**
     * Creates the view holder containing items.
     * @param parent ViewGroup
     * @param viewType int
     * @return
     */
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.task_layout, parent, false);
        return new TaskViewHolder(itemView, this);
    }

    /**
     * Binds each item with the appropriate information and functionality.
     * @param holder TaskViewHolder
     * @param position int
     */
    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {

        String task_name = taskList.get(position).get(DBHelper.TASKS_COL_TASK_NAME);
        String task_status = taskList.get(position).get(DBHelper.TASKS_COL_STATUS);
        String task_date = taskList.get(position).get(DBHelper.TASKS_COL_DATE);
        String task_time= taskList.get(position).get(DBHelper.TASKS_COL_TIME);
        String task_category = taskList.get(position).get(DBHelper.TASKS_COL_CATEGORY);
        String task_priority = taskList.get(position).get(DBHelper.TASKS_COL_PRIORITY);
        
        TextView txt_task_name = holder.taskName;
        TextView txt_task_status = holder.taskStatus;
        TextView txt_task_date = holder.taskDate;
        TextView txt_task_time = holder.taskTime;
        TextView txt_task_category = holder.category_ans_box;
        TextView txt_task_priority = holder.priority_ans_box;
        Button btnRemoveTask = holder.btnRemoveTask;

        //set text
        txt_task_name.setText(task_name);
        txt_task_status.setText(task_status);
        txt_task_date.setText(task_date);
        txt_task_time.setText(task_time);
        txt_task_category.setText(task_category);
        txt_task_priority.setText(task_priority);

        // set onclick listener to remove a task
        btnRemoveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int task_id = Integer.parseInt(taskList.get(position).get(DBHelper.TASKS_COL_ID));
                DBHelper db = new DBHelper(btnRemoveTask.getContext());

                // debug
                Log.i("DB Update", "Updated TID = " + task_id + " USER ID = " + Login.USER_ID);

                if(db.updateUserTask(task_id, Login.USER_ID)) {
                    Toast.makeText(btnRemoveTask.getContext(), "Finished " + task_name + "!", Toast.LENGTH_SHORT).show();
                }

                taskList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    /**
     * Return size of task list.
     * @return int
     */
    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public final TextView taskName;
        public final TextView taskStatus;
        public final TextView taskDate;
        public final TextView taskTime;
        public final TextView category_ans_box;
        public final TextView priority_ans_box;
        public final Button btnRemoveTask;
        final CustomTaskAdapter customTaskAdapter;

        /**
         * Overloaded Constructor
         * @param itemView View
         * @param customTaskAdapter CustomTaskAdapter
         */
        public TaskViewHolder(View itemView, CustomTaskAdapter customTaskAdapter) {
            super(itemView);

            this.taskName = itemView.findViewById(R.id.taskName);
            this.taskStatus = itemView.findViewById(R.id.taskDetail);
            this.taskDate = itemView.findViewById(R.id.taskDate);
            this.taskTime = itemView.findViewById(R.id.taskTime);
            this.category_ans_box = itemView.findViewById(R.id.category_ans_box);
            this.priority_ans_box = itemView.findViewById(R.id.priority_ans_box);
            this.btnRemoveTask = itemView.findViewById(R.id.btnRemoveTask);
            this.customTaskAdapter = customTaskAdapter;

        }


    }
}
