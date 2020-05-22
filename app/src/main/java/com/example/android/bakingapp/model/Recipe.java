package com.example.android.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Recipe implements Parcelable {



    private String mName;
    private ArrayList<Ingredient> mIngredients;
    private ArrayList<Step> mSteps;
    private int mServings;
    private String mImage;

    private boolean mIsFavorite;


    public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, int servings, String image) {
        mName = name;
        mIngredients = ingredients;
        mSteps = steps;
        mServings = servings;
        mImage = image;
    }

    private Recipe(Parcel in) {
        mName = in.readString();
        mIngredients = in.createTypedArrayList(Ingredient.CREATOR);
        mSteps = in.createTypedArrayList(Step.CREATOR);
        mServings = in.readInt();
        mImage = in.readString();
        mIsFavorite = in.readInt() == 1;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeTypedList(mIngredients);
        parcel.writeTypedList(mSteps);
        parcel.writeInt(mServings);
        parcel.writeString(mImage);
        parcel.writeInt(mIsFavorite ? 1 : 0);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int i) {
            return new Recipe[i];
        }
    };




    public String getRecipeName() { return mName; }
    public ArrayList<Ingredient> getRecipeIngredients() { return mIngredients; }
    public ArrayList<Step>  getRecipeSteps() { return mSteps; }
    public int getRecipeServings() { return mServings; }
    public String getRecipeImage() { return mImage; }
    public boolean getIsFavorite() { return mIsFavorite; }




    public void setRecipeName(String name) { mName = name; }
    public void setRecipeIngredients(ArrayList<Ingredient> ingredients) { mIngredients = ingredients; }
    public void setRecipeSteps(ArrayList<Step>  steps) { mSteps = steps; }
    public void setRecipeServings(int servings) { mServings = servings; }
    public void setRecipeImage(String image) { mImage = image; }
    public void setIsFavorite(boolean isFavorite) { mIsFavorite = isFavorite; }
}