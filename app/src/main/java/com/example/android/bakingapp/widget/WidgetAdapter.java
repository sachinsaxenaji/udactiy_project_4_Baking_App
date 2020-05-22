package com.example.android.bakingapp.widget;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;

import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.database.AppDatabase;


public class WidgetAdapter implements RemoteViewsService.RemoteViewsFactory  {
    private  Context mContext;

    private Cursor mCursor;




    String[] list;


    public WidgetAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public void onCreate() {


    }

    @Override
    public void onDataSetChanged() {
        if (mCursor != null) {
            mCursor.close();
        }

        final long identityToken = Binder.clearCallingIdentity();
        AppDatabase db = AppDatabase.getDatabase(mContext);
        mCursor = db.favoriteDao().getCursorAll();

        Binder.restoreCallingIdentity(identityToken);


    }

    @Override
    public void onDestroy() {
        if (mCursor != null) {
            mCursor.close();
        }
    }

    @Override
    public int getCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(final int position) {

        if (position == AdapterView.INVALID_POSITION ||
                mCursor == null || !mCursor.moveToPosition(position)) {
            return null;
        }

        final RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);

        remoteViews.setTextViewText(R.id.widget_tv, mCursor.getString(1));
        Intent intent =new Intent();
        intent.putExtra(WidgetProvider.WIDGET_KEY_ITEM,mCursor.getString(1));
        remoteViews.setOnClickFillInIntent(R.id.widget_rv, intent);
        return remoteViews;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    @Override
    public long getItemId(int position) {
        return mCursor.moveToPosition(position) ? mCursor.getLong(0) : position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }




}
