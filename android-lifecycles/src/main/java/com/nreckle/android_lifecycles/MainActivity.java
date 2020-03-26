package com.nreckle.android_lifecycles;

import android.os.Bundle;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.nreckle.android_lifecycles.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        ChronometerViewModel chronometerViewModel =
                new ViewModelProvider(this).get(ChronometerViewModel.class);
        binding.setViewmodel(chronometerViewModel);
        binding.setLifecycleOwner(this);
        if (chronometerViewModel.getStartTime() == -1L) {
            long startTime = SystemClock.elapsedRealtime();
            chronometerViewModel.setStartTime(startTime);
            binding.chronometer.setBase(startTime);
        } else {
            binding.chronometer.setBase(chronometerViewModel.getStartTime());
        }

        binding.chronometer.start();
    }
}
