package com.example.bbbmeetings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bbbmeetings.databinding.ActivitySettingsBinding;

public class Settings extends AppCompatActivity {
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}