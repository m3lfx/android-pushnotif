package com.example.pushnotif;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

//authController
//var serviceAccount = require('../config/firebase.json'); //download service account
//admin.initializeApp({
//    credential: admin.credential.cert(serviceAccount),
//            databaseURL: "https://app.firebaseio.com" // generate new key on firebase console
//});
//
//

//exports.sendMessage = async (req, res, next) => {
//
//        const topic = 'general';
//
//        const message = {
//notification: {
//title: 'Message from node',
//body: 'hey there',
//imageUrl: 'https://res.cloudinary.com/dgneiaky7/image/upload/v1728010631/products/pstnuskit4hycbl81dlb.png'
//        },
//topic: topic
//    };
//            try {
//response = await admin.messaging().send(message)
//        console.log('Node oct 18:', response);
//        return res.status(200).json({ message: 'success sent message', response })
//        } catch (error) {
//        console.log('Error sending message:', error);
//        return res.status(200).json({ message: error })
//
//        }
//        }

//auth routes
//router.get('/send', sendMessage );

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notification_channel", "notification_channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed Successfully";
                        if (!task.isSuccessful()) {
                            msg = "Subscription failed";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}