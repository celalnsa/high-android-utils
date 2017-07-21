package com.hehigh.hnoti;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

/**
 * Created by high on 17-2-8.
 */

public class HNoti {
//    public static void ding() {
//        Bitmap icon = BitmapFactory.decodeResource(getResources(),
//                R.drawable.high_icon);
//        NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(this)
//                        .setOngoing(true)
//                        .setPriority(NotificationCompat.PRIORITY_MAX)
//                        .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
//                        .setContentTitle("My notification")
//                        .setContent(new RemoteViews(getPackageName(), R.layout.big_content_view))
//                        .setCustomBigContentView(new RemoteViews(getPackageName(), R.layout.big_content_view))
//                        .setCustomContentView(new RemoteViews(getPackageName(), R.layout.normal_content_view))
//                        .setContentText("xxxx")
//                        .setSmallIcon(R.drawable.high_icon);
//        Intent resultIntent = new Intent(this, ResultActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(ResultActivity.class);
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(resultPendingIntent);
//        Notification notification = builder.build();
//        int mNotificationId = 2;
//        NotificationManager mNotifyMgr =
//                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        mNotifyMgr.notify(mNotificationId, notification);
//    }
}
