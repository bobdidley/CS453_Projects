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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.AddTaskActivity;
import com.example.todolist.TaskActivity;
import com.example.todolist.databinding.FragmentTaskBinding;

public class TaskFragment extends Fragment {

    private TaskViewModel galleryViewModel;
    private FragmentTaskBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(TaskViewModel.class);

        binding = FragmentTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        binding.btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTask = new Intent(getActivity(), AddTaskActivity.class);
                startActivity(addTask);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}