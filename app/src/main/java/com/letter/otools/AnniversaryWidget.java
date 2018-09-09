package com.letter.otools;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.letter.otools.util.AnniUtil;

/**
 * Implementation of App Widget functionality.
 */
public class AnniversaryWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        freshWidget(context);

//        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.anniversary_widget);
//        Anniversary anniversary = AnniUtil.getClosestAnni();
//        views.setTextViewText(R.id.anni_text, anniversary.getText());
//        views.setTextViewText(R.id.anni_date, anniversary.getDateText());
//        views.setTextViewText(R.id.anni_type, anniversary.getTypeText());
//        views.setTextViewText(R.id.anni_days, anniversary.getDaysText());
//
//        // Instruct the widget manager to update the widget
//        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        freshWidget(context);
    }

    private static void freshWidget (Context context) {
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, AnniversaryWidget.class);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.anniversary_widget);
        Anniversary anniversary = AnniUtil.getClosestAnni();
        views.setTextViewText(R.id.anni_text, anniversary.getText());
        views.setTextViewText(R.id.anni_date, anniversary.getDateText());
        views.setTextViewText(R.id.anni_type, anniversary.getTypeText());
        views.setTextViewText(R.id.anni_days, anniversary.getDaysText());
        Intent intentAdd = new Intent(context, AddItemActivity.class);
        PendingIntent pendingIntentAdd = PendingIntent.getActivity(context, 0, intentAdd, 0);
        views.setOnClickPendingIntent(R.id.widget_add, pendingIntentAdd);
        Intent intentItem =  new Intent(context, AnniversaryActivity.class);
        intentItem.putExtra("anniId", anniversary.getId());
        PendingIntent pendingIntentItem = PendingIntent.getActivity(context, 1, intentItem, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setOnClickPendingIntent(R.id.anni_widget_item, pendingIntentItem);
        manager.updateAppWidget(componentName, views);
    }

}

