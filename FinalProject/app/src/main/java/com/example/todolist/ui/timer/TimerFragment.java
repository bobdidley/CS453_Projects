/**
 * Timer page.
 *
 * @file TimerFragment.java
 * @authors Fiona Le & James Austin Jr.
 * @date 07/01/2021
 * @version 1.0
 */

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

import com.example.todolist.R;

public class TimerFragment extends Fragment {

    private EditText timeInput;
    private TextView timeDisplay;
    private Button btnStart;
    private Button btnStop;
    private Button btnReset;

    CountDownTimer timer;
    private long time_in_ms;
    private String initialTime;

    // for time conversions
    private final int min_ms = 60000;
    private final int sec_ms = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        if(view != null) {
            timeInput = view.findViewById(R.id.timeInput);
            timeDisplay = view.findViewById(R.id.timeDisplay);
            btnStart = view.findViewById(R.id.btnStart);
            btnStop = view.findViewById(R.id.btnStop);
            btnReset = view.findViewById(R.id.btnReset);

            // debug
//            Log.i("Init Btns", "Buttons are initialized");
        }

        // timer start button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        // timer reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        // timer stop button
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        return view;
    }

    /**
     * Starts the time with the set value.
     */
    private void startTimer() {
        initialTime = timeInput.getText().toString();
        timeDisplay.setText(initialTime);
        timeInput.setVisibility(View.INVISIBLE);
        timeDisplay.setVisibility(View.VISIBLE);

        btnStart.setClickable(false);
        btnReset.setClickable(true);
        btnStop.setClickable(true);

        String[] min_sec = initialTime.split(":");
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
    }

    /**
     * Resets the timer to the last value from Start.
     */
    private void resetTimer() {
        timer.cancel();
        timeDisplay.setText(initialTime);
        timeInput.setText(initialTime);
        timeDisplay.setVisibility(View.INVISIBLE);
        timeInput.setVisibility(View.VISIBLE);

        btnStart.setClickable(true);
        btnReset.setClickable(false);
        btnStop.setClickable(false);
    }

    /**
     * Stops the timer where it's at.
     */
    private void stopTimer() {
        timer.cancel();

        btnStart.setClickable(true);
        btnReset.setClickable(true);
        btnStop.setClickable(false);
    }

    /**
     * Updates the timer.
     */
    private void updateTimer() {
        int minute = (int) time_in_ms / min_ms;
        int second = (int) time_in_ms % min_ms / sec_ms;

        StringBuilder timeLeft = new StringBuilder();
        if(minute < 10) timeLeft.append(0);
        timeLeft.append(minute);
        timeLeft.append(":");
        if(second < 10) timeLeft.append(0);
        timeLeft.append(second);

        timeDisplay.setText(timeLeft.toString());
        timeInput.setText(timeLeft.toString());
    }

    public TimerFragment() {}

}