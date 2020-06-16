package com.techolution.services;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class ExampleJobIntentService extends JobIntentService {

    static void enqueueWork(Context context, Intent intent){
        enqueueWork(context,ExampleJobIntentService.class,123,intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    public static final String TAG = "ExampleJobIntentService";
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG,"onHandleWork");
        String input = intent.getStringExtra("inputExtra");
        for(int i= 0; i<10;i++){
            Log.i("service",""+input+" - "+i);

            if(isStopped()) return;

            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    public boolean onStopCurrentWork() {
        return super.onStopCurrentWork();
        //return false to drop the job
        //default returns true

    }
}
