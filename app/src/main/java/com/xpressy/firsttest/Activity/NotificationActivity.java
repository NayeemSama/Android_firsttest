package com.xpressy.firsttest.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.xpressy.firsttest.R;

public class NotificationActivity extends BaseActivity{

    EditText etMainNotificationText, etMainNotificationTitle;
    Button btnMainSendSimpleNotification;
    NotificationManager notificationManager;
    NotificationCompat.Builder notificationBuilder;
    String notificationTitle = "TITLE";
    String notificationText = "notificationText,notificationText,notificationText....n";
    String channelId = "i.apps.notifications";
    String description = "Test notification";
    NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initRef();
        initEvent();

    }

    private void initEvent() {
        btnMainSendSimpleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                Intent intent = new Intent(NotificationActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this, channelId)
                        .setSmallIcon(R.drawable.ic_star)
                        .setContentTitle(notificationTitle)
                        .setContentText(description)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "channel_name";
                    String description = "channel_description";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel channel = new NotificationChannel(channelId, name, importance);
                    channel.setDescription(description);

                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }

                notificationManagerCompat = NotificationManagerCompat.from(NotificationActivity.this);

                notificationManagerCompat.notify(1234, builder.build());

            }
        });
    }

    private void initRef() {

        etMainNotificationText =  findViewById(R.id.etMainNotificationText);
        etMainNotificationTitle =  findViewById(R.id.etMainNotificationTitle);
        btnMainSendSimpleNotification =  findViewById(R.id.btnMainSendSimpleNotification);
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

    }
}

