package com.techolution.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.techolution.services.databinding.ActivityMainBinding;

public class JobSchedulerActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.startService.setText("Schedule Job");
        binding.stopService.setText("Cancel Job");
        binding.startService.setOnClickListener(v -> scheduleJob());
        binding.stopService.setOnClickListener(v -> cancelJob());
    }

    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this,ExampleJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true) // runs after reboot
                .setPeriodic(15*60*1000)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        assert jobScheduler != null;
        int resultCode = jobScheduler.schedule(jobInfo);
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d("JobSchedulerActivity","job scheduled succesfully");
        }else {
            Log.d("JobSchedulerActivity","job not scheduled");
        }
    }

    public void cancelJob(){
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(123);
        Log.d("JobSchedulerActivity","job cancelled");
    }
}