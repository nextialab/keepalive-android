package com.nextialab.keepalive;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Date;

/**
 * Created by nigonzalez on 9/14/16.
 */
public class MyAlarmManager {

    public static final String KEEP_ALIVE_INTENT = "com.nextialab.keepalive.AlarmReceiver";
    public static final String PERIOD = "period";
    public static final int REQUEST_CODE = 42;

    public static void setAlarm(Context context, int period) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction(KEEP_ALIVE_INTENT);
        intent.putExtra(PERIOD, period);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long now = (new Date()).getTime();
        alarmManager.set(AlarmManager.RTC_WAKEUP, now + period * 1000, pendingIntent);
    }

    public static void removeAlarm(Context context, int period) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction(KEEP_ALIVE_INTENT);
        intent.putExtra(PERIOD, period);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

}
