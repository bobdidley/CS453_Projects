package com.example.todolist.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.AddTaskActivity;
import com.example.todolist.CalendarActivity;
import com.example.todolist.CustomTaskAdapter;
import com.example.todolist.R;
import com.example.todolist.TaskActivity;
import com.example.todolist.databinding.FragmentTaskBinding;
import com.example.todolist.ui.calendar.CalendarFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskFragment extends Fragment {

    private TaskViewModel galleryViewModel;
    private FragmentTaskBinding binding;
    CustomTaskAdapter adapter;
    ArrayList<HashMap<String,String>> data = new ArrayList<>();
    private java.util.ArrayList<HashMap<String, String>> ArrayList;
    private FloatingActionButton add;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(TaskViewModel.class);

        binding = FragmentTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       View view = inflater.inflate(R.layout.fragment_task,container,false);
       add = view.findViewById(R.id.btnAddTask);

//        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTask = new Intent(getActivity(), AddTaskActivity.class);
                startActivity(addTask);
            }
        });


        return view;
    }




    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.taskRecyclerView.setLayoutManager(manager);
        binding.taskRecyclerView.setHasFixedSize(true);
        adapter = new CustomTaskAdapter(getActivity());
        binding.taskRecyclerView.setAdapter(adapter);
      // adapter.setTasksList((ArrayList<HashMap<String,String>>) data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}