package com.example.android.bakingapp.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import android.arch.persistence.room.Ignore;

import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


@Entity(tableName = "favorite_table")
public class FavoriteEntry implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "recipe_name")
    private String recipeName;
    @ColumnInfo(name = "recipe_ingredients")
    private String recipeIngredient;





    public FavoriteEntry(){}

    public FavoriteEntry(   String name,String ingredients) {

        this.recipeName = name;
        this.recipeIngredient = ingredients;

    }



    public String getRecipeName() {
        return this.recipeName;
    }

    public void setRecipeName(String name) {
        this.recipeName = name;
    }

    public String getRecipeIngredient() {
        return this.recipeIngredient;
    }

    public void setRecipeIngredient(String ingredient) {
        this.recipeIngredient = ingredient;
    }





    // Parcelling part
    @Ignore
    public FavoriteEntry(Parcel parcel_in){

        recipeName = parcel_in.readString();
        recipeIngredient=parcel_in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(recipeName);
        dest.writeString(recipeIngredient);

    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public FavoriteEntry createFromParcel(Parcel in) {
            return new FavoriteEntry(in);
        }

        public FavoriteEntry[] newArray(int size) {
            return new FavoriteEntry[size];
        }
    };


}
