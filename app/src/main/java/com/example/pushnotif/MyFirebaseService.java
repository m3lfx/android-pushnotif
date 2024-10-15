package com.example.pushnotif;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;

public class MyFirebaseService extends FirebaseMessagingService {
    private final static String CHANNEL_ID = "1";
    private final static String CHANNEL_NAME = "1";
    private int counter = 0;
    NotificationCompat.Builder builder;
    NotificationManagerCompat compat;
    private final static int NOTIFICATION_ID = 1;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        NotificationManager notificationManager = (NotificationManager) getSystemService ( Context.NOTIFICATION_SERVICE );
        if (!Objects.equals ( null, message.getNotification () )) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel ( CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH );
                notificationManager.createNotificationChannel ( notificationChannel );
            }
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder ( this, CHANNEL_ID );
            notificationBuilder.setAutoCancel ( true )
                    .setStyle ( new NotificationCompat.BigTextStyle ().bigText ( message.getNotification ().getBody () ) )
                    .setDefaults ( Notification.DEFAULT_ALL )
                    .setWhen ( System.currentTimeMillis () )
                    .setSmallIcon ( R.drawable.notification_icon )
                    .setTicker( message.getNotification ().getTitle () )
                    .setPriority( Notification.PRIORITY_MAX )
                    .setContentTitle( message.getNotification ().getTitle() )
                    .setContentText( message.getNotification ().getBody() );
            notificationManager.notify ( 1, notificationBuilder.build () );
        }

    }
}
