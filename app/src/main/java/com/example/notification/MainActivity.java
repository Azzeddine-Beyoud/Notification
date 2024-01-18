package com.example.notification;

import static com.example.notification.App.CHANNEL_1_ID;
import static com.example.notification.App.CHANNEL_2_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManagerCompat;
    private EditText editTextTitle;
    private EditText editTextMessage;
    private MediaSessionCompat mediaSessionCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        editTextMessage = findViewById(R.id.edit_text_message);
        editTextTitle = findViewById(R.id.edit_text_title);

        mediaSessionCompat = new MediaSessionCompat(this , "tag");
    }

    public void sendOnChannel1(View v){

        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, PendingIntent.FLAG_IMMUTABLE);

//        Intent broadcastIntent = new Intent(this , NotificationReceiver.class);
//        broadcastIntent.putExtra("toastMessage", message);
//        PendingIntent actionIntent = PendingIntent.getBroadcast(this ,
//                0 ,broadcastIntent,Pending Intent.FLAG_IMMUTABLE);

        Bitmap picture = BitmapFactory.decodeResource(getResources(),R.drawable.lotti1);

        Notification notification = new NotificationCompat.Builder(this , CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();
        notificationManagerCompat.notify(1,notification);
    }
    public void sendOnChannel2(View v){
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Bitmap artwork = BitmapFactory.decodeResource(getResources(),R.drawable.lotti);

        Notification notification = new NotificationCompat.Builder(this , CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(artwork)
//                .addAction(R.drawable.ic_check , "check", null)
//                .addAction(R.drawable.ic_previous , "previous", null)
//                .addAction(R.drawable.ic_pause , "pause", null)
//                .addAction(R.drawable.ic_next , "next", null)
//                .addAction(R.drawable.ic_close , "close", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1,2,3)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                .setSubText("Sub Text")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        notificationManagerCompat.notify(2, notification);
    }
}

//.setStyle(new NotificationCompat.BigTextStyle()
//        .bigText(getString(com.google.android.gms.base.R.string.common_signin_button_text_long))
//        .setBigContentTitle("Big Content Title")
//        .setSummaryText("Summary text"))
//.setColor(Color.BLUE)
//.addAction(R.mip.ic_launcher,"Toast" ,actionIntent)