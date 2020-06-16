package com.techolution.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExampleJobService extends JobService {
    public static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"onStartJob");

        // Job services runs on UI thread
        //if the task is short and can be completed within the scope
        //return false;


        // For long running task where we are offloading task to a separate thread return true
        doBackgroundWork(params);
        return true;
    }

    private void doBackgroundWork(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i= 0; i<10;i++){
                    Log.i("service: ",""+i);
                    if(jobCancelled) return;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //true to reschedule
                //false, not to reschedule
                jobFinished(params,false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG,"job cancelled before completion");
        //return true if you want to reschedule
        // return false, not to reschedule
        jobCancelled = true;
        return true;
    }
}
