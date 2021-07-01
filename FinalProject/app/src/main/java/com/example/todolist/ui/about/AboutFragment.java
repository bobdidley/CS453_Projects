package com.example.todolist.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todolist.R;
import com.example.todolist.databinding.FragmentAboutBinding;
import com.example.todolist.databinding.FragmentHelpBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {
    private TextView txtView1, txtView2, txtView3, txtView4, txtView5, txtView6, txtView7;
    public FragmentAboutBinding binding;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_about, container, false);

        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


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