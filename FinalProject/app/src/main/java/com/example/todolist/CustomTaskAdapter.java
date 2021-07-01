package com.example.todolist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.ui.gallery.TaskFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;


public class CustomTaskAdapter extends RecyclerView.Adapter<CustomTaskAdapter.TaskViewHolder> {

    ArrayList<HashMap<String,String>> taskList;
    private LayoutInflater inflater;

    /**
     * Overloaded constructor
     * @param context Context
     */
    public CustomTaskAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
        this.taskList = new ArrayList<>();
    }

    /**
     * Clears the vehicle list array and updates so a new vehicle list can be put in.
     */
    public void reset() {
        this.taskList.clear();
        notifyDataSetChanged();
    }

    /**
     * Sets the new vehicle list.
     * @param taskList ArrayList<HashMap<String, String>>
     */
    public void setTasksList(ArrayList<HashMap<String,String>> taskList){
        this.taskList = taskList;
        notifyDataSetChanged();
    }
//
//    public CustomTaskAdapter(Context context, List<taskModel> list) {
//        this.taskList = list;
//        this.inflater = LayoutInflater.from(context);
//    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.task_layout,parent,false);
        return new TaskViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {

        String task_name = taskList.get(position).get("task_name");
        String task_status = taskList.get(position).get("status");
        String task_date = taskList.get(position).get("date");
        String task_time= taskList.get(position).get("time");
        String task_category = taskList.get(position).get("category");
        String task_priority = taskList.get(position).get("priority");


        TextView txt_task_name = holder.itemView.findViewById(R.id.taskName);
        TextView txt_task_status = holder.itemView.findViewById(R.id.taskDetail);
        TextView txt_task_date = holder.itemView.findViewById(R.id.taskDate);
        TextView txt_task_time = holder.itemView.findViewById(R.id.taskTime);
        TextView txt_task_category = holder.itemView.findViewById(R.id.category_ans_box);
        TextView txt_task_priority = holder.itemView.findViewById(R.id.priority_ans_box);

        //set text
        txt_task_name.setText(task_name);
        txt_task_status.setText(task_status);
        txt_task_date.setText(task_date);
        txt_task_time.setText(task_time);
        txt_task_category.setText(task_category);
        txt_task_priority.setText(task_priority);

    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    private boolean toBoolean(int n){
        return n!=0;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView taskName;
        public final TextView taskStatus;
        public final TextView taskDate;
        public final TextView taskTime;
        public final TextView category_ans_box;
        public final TextView priority_ans_box;

        public TaskViewHolder(View itemView) {
            super(itemView);

            this.taskName = itemView.findViewById(R.id.taskName);
            this.taskStatus = itemView.findViewById(R.id.taskDetail);
            this.taskDate = itemView.findViewById(R.id.taskDate);
            this.taskTime = itemView.findViewById(R.id.taskTime);
            this.category_ans_box = itemView.findViewById(R.id.category_ans_box);
            this.priority_ans_box = itemView.findViewById(R.id.priority_ans_box);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {


            int position = getLayoutPosition();
            final TaskActivity mParentActivity = (TaskActivity) inflater.getContext();

            TaskFragment frg = new TaskFragment();

            // send task details through a Bundle
            Bundle args = new Bundle();
            args.putSerializable("details", taskList.get(position));
            frg.setArguments(args);

            mParentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_task, frg)
                    .addToBackStack(null)
                    .commit();


        }
    }
}
