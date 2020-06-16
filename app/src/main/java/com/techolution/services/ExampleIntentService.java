package com.techolution.services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.techolution.services.App.CHANNEL_ID;

public class ExampleIntentService extends IntentService {

    private PowerManager.WakeLock wakeLock;

    @Override
    public void onCreate() {
        super.onCreate();

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"ExampleApp:Wakelock");
        wakeLock.acquire();
        Log.d("Wakelock", "acquired");

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Intent Service")
                .setContentText("running..")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        startForeground(1,notification);
    }


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public ExampleIntentService() {
        super("ExampleIntentService");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("ExampleIntentService","onHandleIntent");
        String input = intent.getStringExtra("inputExtra");
        for(int i= 0; i<10;i++){
            Log.i("service",""+input+" - "+i);
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wakeLock.release();
        Log.d("Wakelock", "released");
    }
}
