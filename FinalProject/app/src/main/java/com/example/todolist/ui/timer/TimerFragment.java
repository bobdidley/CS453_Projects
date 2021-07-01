package com.example.todolist.ui.timer;

import android.os.Bundle;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import com.example.todolist.AddTaskActivity;
import com.example.todolist.R;
import com.example.todolist.databinding.ActivityProfileBinding;
import com.example.todolist.databinding.FragmentTaskBinding;
import com.example.todolist.databinding.FragmentTimerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimerFragment extends Fragment {

    private TimerViewModel timerViewModel;
    private FragmentTimerBinding binding;

    private EditText timeInput;
    private TextView timeDisplay;
    private Button btnStart;
    private Button btnStop;
    private Button btnReset;

    CountDownTimer timer;
    private long time_in_ms;

    private final int min_ms = 60000;
    private final int sec_ms = 1000;

    private boolean isRunning;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        timerViewModel =
                new ViewModelProvider(this).get(TimerViewModel.class);
        binding = FragmentTimerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(container != null) {
            timeInput = container.findViewById(R.id.timeInput);
            timeDisplay = container.findViewById(R.id.timeDisplay);
            btnStart = container.findViewById(R.id.btnStart);

            // debug
            Log.i("Init Start", "btnStart was initialized");

            btnStop = container.findViewById(R.id.btnStop);
            btnReset = container.findViewById(R.id.btnReset);
        }

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_timer, container, false);

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        
        return root;
    }

    private void startTimer() {
        String time = timeInput.getText().toString();
        timeDisplay.setText(time);
        timeInput.setVisibility(View.INVISIBLE);
        timeDisplay.setVisibility(View.VISIBLE);

        String[] min_sec = time.split(":");
        int minute_ms = Integer.parseInt(min_sec[0]) * min_ms;
        int second_ms = Integer.parseInt(min_sec[1]) * sec_ms;
        time_in_ms = minute_ms + second_ms;

        timer = new CountDownTimer(time_in_ms, sec_ms) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_in_ms = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
            }
        }.start();

        isRunning = true;
    }

    private void updateTimer() {
        int minute = (int) time_in_ms / min_ms;
        int second = (int) time_in_ms % min_ms / sec_ms;

        StringBuilder timeLeft = new StringBuilder();
        timeLeft.append(minute);
        timeLeft.append(":");
        if(second < 10) timeLeft.append(0);
        timeLeft.append(second);

        timeDisplay.setText(timeLeft.toString());
    }

    public TimerFragment() {}

    public static TimerFragment newInstance(String param1, String param2) {
        TimerFragment fragment = new TimerFragment();
        return fragment;
    }
}