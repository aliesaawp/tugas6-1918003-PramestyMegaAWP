package com.example.tgs6_workmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker{
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Result doWork() {
        for(int x=1; x<=4; x++){
            try {
                Thread.sleep(1700);
                displayNotification("Aplikasi Apa", x + " Pesan baru diterima");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayNotification(String task, String desc){
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel("test", "aplikasiapa", NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),
                "test")
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.drawable.ic_chat)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        manager.notify(1, builder.build());
    }

}

