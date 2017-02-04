package com.acp.terjelonoy.androidcertificationpreparation.managers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.acp.terjelonoy.androidcertificationpreparation.receivers.AlarmReceiver;

/**
 * Created by terjelonoy on 03/02/2017.
 */

public class AlarmScheduleManager {
    private static final int ALARM_WAKE_ID = 251;

    private static AlarmManager manager;
    private static PendingIntent pendingIntent;

    public static void setAlarm(Context context) {
        manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, ALARM_WAKE_ID, intent, 0);

        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 15 * 1000,
                pendingIntent);
    }
}
