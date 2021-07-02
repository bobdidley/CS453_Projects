package com.example.todolist.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todolist.R;

public class AboutFragment extends Fragment {
    private TextView txtView1, txtView2, txtView3, txtView4, txtView5, txtView6, txtView7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about,container,false);

        txtView1 = view.findViewById(R.id.line1);
        txtView2 = view.findViewById(R.id.line2);
        txtView3 = view.findViewById(R.id.line3);
        txtView4 = view.findViewById(R.id.line4);
        txtView5 = view.findViewById(R.id.line5);
        txtView6 = view.findViewById(R.id.line6);
        txtView7 = view.findViewById(R.id.line7);


        txtView1.setText("TO-DO LIST");
        txtView2.setText("Daily productivity app that keeps track of tasks.");
        txtView3.setText("Contributors: Fiona Le & James Austin Jr.");
        txtView4.setText("Sponsors: AJ Fahim");
        txtView5.setText("Version #: 1.0");
        txtView6.setText("Copyright: Summer 2021");
        txtView7.setText("Donate here: GimmeMoney.com");

        return view;
    }
}