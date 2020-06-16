package com.techolution.services;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.techolution.services.databinding.ActivityMainBinding;

public class JobIntentServiceActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.startService.setOnClickListener(v -> startService());
    }

    public void startService() {
        Intent serviceIntent = new Intent(this, ExampleJobIntentService.class);
        serviceIntent.putExtra("inputExtra", binding.editText.getText().toString().trim());
        ExampleJobIntentService.enqueueWork(this,serviceIntent);
    }
}