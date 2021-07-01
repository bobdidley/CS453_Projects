package com.example.todolist.ui.help;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todolist.R;
import com.example.todolist.databinding.FragmentHelpBinding;
import com.example.todolist.databinding.FragmentTaskBinding;

public class HelpFragment extends Fragment {

    private HelpViewModel mViewModel;
    private TextView txtView1, txtView2, txtView3, txtView4, txtView5, txtView6;
    public FragmentHelpBinding binding;

    public static HelpFragment newInstance() {
        return new HelpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.fragment_help, container, false);

        binding = FragmentHelpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        View view = inflater.inflate(R.layout.fragment_help,container,false);

        txtView1 = view.findViewById(R.id.line1);
        txtView2 = view.findViewById(R.id.line2);
        txtView3 = view.findViewById(R.id.line3);
        txtView4 = view.findViewById(R.id.line4);
        txtView5 = view.findViewById(R.id.line5);
        txtView6 = view.findViewById(R.id.line6);


        txtView1.setText("We dont even know how to fix it, so you are on your own");
        txtView2.setText("Contact us at 1-800-NEVER OR email us at never.ever@help.com");
        txtView3.setText("FAQ");
        txtView4.setText("How do you work this app? - idk");
        txtView5.setText("Who forced you to build this?- AJFahim");
        txtView6.setText("How much is the pro version? - Free.99");
        return view;

//    public void onActivityCreated( Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(HelpViewModel.class);
//        // TODO: Use the ViewModel
//    }

}}