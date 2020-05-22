package com.example.android.bakingapp.ui.recipe;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.bakingapp.database.AppDatabase;
import com.example.android.bakingapp.database.FavoriteDao;
import com.example.android.bakingapp.database.FavoriteEntry;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private FavoriteDao mFaveDao;
    private LiveData<List<FavoriteEntry>> mAllFavoriteMovies;



    public RecipeViewModel (Application application) {
         super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        mFaveDao = db.favoriteDao();
        mAllFavoriteMovies = mFaveDao.getAllFavorites();

    }
    public LiveData<List<FavoriteEntry>> getAllFavorites() { return mAllFavoriteMovies; }

    public void insertFavorite(FavoriteEntry fave) {
        new insertAsyncTask(mFaveDao).execute(fave);
    }

    private static class insertAsyncTask extends AsyncTask<FavoriteEntry, Void, Void> {

        private FavoriteDao mAsyncTaskDao;

        insertAsyncTask(FavoriteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavoriteEntry... params) {
            mAsyncTaskDao.insertFavorite(params[0]);
            return null;
        }


    }

    public void deleteFavorite(String favoriteName) {
        new deleteAsyncTask(mFaveDao).execute(favoriteName);
    }

    private static class deleteAsyncTask extends AsyncTask<String, Void, Void> {

        private FavoriteDao mAsyncTaskDao;

        deleteAsyncTask(FavoriteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.deleteFavorite(params[0]);
            return null;
        }


    }

}
