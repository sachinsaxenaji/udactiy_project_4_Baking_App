package com.example.android.bakingapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.bakingapp.database.AppDatabase;
import com.example.android.bakingapp.database.FavoriteDao;
import com.example.android.bakingapp.database.FavoriteEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;



@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteDBTest{
    private FavoriteDao mFavDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mFavDao = mDb.favoriteDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeFavoriteAndDelete() throws Exception {

        FavoriteEntry favoriteEntry = new FavoriteEntry("Test Recipe","Test Ingredient1,Test Ingredient2");
        mFavDao.insertFavorite(favoriteEntry);
        String byName = mFavDao.findFavorite("Test Recipe");
        mFavDao.deleteFavorite(byName);

    }
}
