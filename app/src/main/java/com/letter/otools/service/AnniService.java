package com.letter.otools.service;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.letter.otools.AddItemActivity;
import com.letter.otools.Anniversary;
import com.letter.otools.AnniversaryActivity;
import com.letter.otools.AnniversaryWidget;
import com.letter.otools.R;
import com.letter.otools.util.AnniUtil;

public class AnniService extends Service {
    public AnniService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        freshWidget();
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void freshWidget () {
        AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
        ComponentName componentName = new ComponentName(getApplicationContext(), AnniversaryWidget.class);
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.anniversary_widget);
        Anniversary anniversary = AnniUtil.getClosestAnni();
        views.setTextViewText(R.id.anni_text, anniversary.getText());
        views.setTextViewText(R.id.anni_date, anniversary.getDateText());
        views.setTextViewText(R.id.anni_type, anniversary.getTypeText());
        views.setTextViewText(R.id.anni_days, anniversary.getDaysText());
        Intent intentAdd = new Intent(getApplicationContext(), AddItemActivity.class);
        PendingIntent pendingIntentAdd = PendingIntent.getActivity(getApplicationContext(), 0, intentAdd, 0);
        views.setOnClickPendingIntent(R.id.widget_add, pendingIntentAdd);
        Intent intentItem =  new Intent(getApplicationContext(), AnniversaryActivity.class);
        intentItem.putExtra("anniId", anniversary.getId());
        PendingIntent pendingIntentItem = PendingIntent.getActivity(getApplicationContext(), 1, intentItem, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setOnClickPendingIntent(R.id.anni_widget_item, pendingIntentItem);
        manager.updateAppWidget(componentName, views);
    }
}
