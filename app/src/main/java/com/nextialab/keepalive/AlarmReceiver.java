package com.nextialab.keepalive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by nigonzalez on 9/14/16.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = AlarmReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Alarm received, setting a new one");
        int period = intent.getIntExtra(MyAlarmManager.PERIOD, 0);
        if (period > 0) {
            MyAlarmManager.setAlarm(context, period);
        } else {
            Log.e(TAG, "Period is zero");
        }
    }

}
