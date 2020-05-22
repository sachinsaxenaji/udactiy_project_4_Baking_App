package com.example.android.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;


import com.example.android.bakingapp.R;






public class WidgetProvider extends AppWidgetProvider {

public static final String WIDGET_KEY_ITEM = "com.example.widget.WIDGET_KEY_ITEM";
public static final String WIDGET_TOAST_ACTION ="com.example.widget.WIDGET_TOAST_ACTION";




    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(WIDGET_TOAST_ACTION)){
            String  listItem = intent.getStringExtra(WIDGET_KEY_ITEM);
            Toast.makeText(context,listItem,Toast.LENGTH_SHORT).show();
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        }
        super.onReceive(context, intent);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        int[] realAppWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context,WidgetProvider.class));
        for(int id:realAppWidgetIds){
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);


            Intent serviceIntent = new Intent(context,WidgetService.class);
            remoteViews.setRemoteAdapter(R.id.widget_lv, serviceIntent);





            Intent intent = new Intent(context, WidgetProvider.class);

             intent.setAction(WIDGET_TOAST_ACTION);

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,realAppWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

            remoteViews.setPendingIntentTemplate(R.id.widget_lv,pendingIntent);




            appWidgetManager.updateAppWidget(id,remoteViews);
            appWidgetManager.notifyAppWidgetViewDataChanged(id, R.id.widget_lv);



        }
    }
}
