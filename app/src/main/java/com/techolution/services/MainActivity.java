package com.techolution.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techolution.services.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.startService.setOnClickListener(v ->startService());
        binding.stopService.setOnClickListener(v -> stopService());
    }

    public void startService(){
        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra",binding.editText.getText().toString().trim());
        this.startService(serviceIntent);
    }

    public void stopService(){
        Intent serviceIntent = new Intent(this, ExampleService.class);
        this.stopService(serviceIntent);
    }
}
