package com.acp.terjelonoy.androidcertificationpreparation.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.acp.terjelonoy.androidcertificationpreparation.MainActivity;
import com.acp.terjelonoy.androidcertificationpreparation.R;

/**
 * Created by terjelonoy on 2/26/17.
 */

public class WidgetProvider extends AppWidgetProvider {
    private Integer count = 1;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_example);

            count = count + 1;
            views.setTextViewText(R.id.widget_counter, count.toString());

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
