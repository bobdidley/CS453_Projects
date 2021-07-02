/**
 * Home page.
 *
 * @file HomeFragment.java
 * @authors Fiona Le & James Austin Jr.
 * @date 07/01/2021
 * @version 1.0
 */

package com.example.todolist.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todolist.AboutActivity;
import com.example.todolist.ChangePasswordActivity;
import com.example.todolist.HelpActivity;
import com.example.todolist.R;
import com.example.todolist.db_contents.DBHelper;

public class HomeFragment extends Fragment {

    private TextView txtUsername;
    private TextView txtPassword;
    private Button btnChangePassword;
//    private Button btnEditTasks;
    private Button btnHelp;
    private Button btnAbout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        DBHelper db = new DBHelper(view.getContext());

        txtUsername = view.findViewById(R.id.username);
        txtPassword = view.findViewById(R.id.password);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);
//        btnEditTasks = view.findViewById(R.id.btnEditTasks);
        btnHelp = view.findViewById(R.id.btnHelp);
        btnAbout = view.findViewById(R.id.btnAbout);

        txtUsername.setText(db.getUsername());
        txtPassword.setText("Password");

        // redirects to change password activity
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changePassword = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(changePassword);
            }
        });

        // redirects to help activity
        btnHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent help = new Intent(getActivity(), HelpActivity.class);
                startActivity(help);
            }

        });

        // redirects to about activity
        btnAbout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent help = new Intent(getActivity(), AboutActivity.class);
                startActivity(help);
            }

        });

//        btnEditTasks.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent editTask = new Intent(getActivity(), TaskActivity.class);
//                startActivity(editTask);
//            }
//        });

        return view;
    }
}