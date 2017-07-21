package com.hehigh.alarmutil;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

/**
 * Created by high on 17-2-8.
 */

public class AlarmUtil {
    public final static String EXTRA_REQUEST_CODE = "request_code";
    public final static String EXTRA_TRIGGER_MILLIS = "trigger_at_millis";
    public final static String EXTRA_TRIGGER_INTERVAL = "trigger_interval";

    public static void createRepeatAlarm(Context context, Intent intent, int requestCode, long triggerAtMillis, long triggerInterval) {
        intent.putExtra(EXTRA_REQUEST_CODE, requestCode);
        intent.putExtra(EXTRA_TRIGGER_MILLIS, triggerAtMillis);
        intent.putExtra(EXTRA_TRIGGER_INTERVAL, triggerInterval);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
        } else {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, triggerInterval, pendingIntent);
        }
    }

    public static void resumeRepeatAlarm(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int requestCode = intent.getIntExtra(EXTRA_REQUEST_CODE, 0);
            long triggerAtMillis = intent.getLongExtra(EXTRA_TRIGGER_MILLIS, 0);
            long triggerInterval = intent.getLongExtra(EXTRA_TRIGGER_INTERVAL, 0);
            long nextTriggerMills = nextTriggerTime(triggerAtMillis, triggerInterval);
            intent.putExtra(EXTRA_TRIGGER_MILLIS, nextTriggerMills);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, nextTriggerMills, pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextTriggerMills, pendingIntent);
            }
        }
    }

    public static void cancelRepeatAlarm(Context context, Intent intent, int requestCode) {
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent.cancel();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    public static long nextTriggerTime(long triggerAtMillis, long triggerInterval) {
        long nowMillis = Calendar.getInstance().getTimeInMillis();
        return triggerAtMillis + (1 + (nowMillis - triggerAtMillis) / triggerInterval) * triggerInterval;
    }
}
