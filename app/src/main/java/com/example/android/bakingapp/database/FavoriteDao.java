package com.example.android.bakingapp.database;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite_table ORDER BY recipe_name")
    LiveData<List<FavoriteEntry>> getAllFavorites();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(FavoriteEntry favEntry);

    @Update
    void updateFavorite(FavoriteEntry favEntry);

    @Query("DELETE FROM favorite_table WHERE recipe_name=:recipe_name")
    void deleteFavorite(String recipe_name);

    @Query("SELECT recipe_name FROM favorite_table WHERE recipe_name=:recipe_name")
    String findFavorite(String recipe_name);

    @Query("DELETE FROM favorite_table")
    void deleteAll();


    @Query("SELECT * FROM favorite_table")
    Cursor getCursorAll();




}
