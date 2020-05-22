package com.example.android.bakingapp;



import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.example.android.bakingapp.model.Recipe;

import com.example.android.bakingapp.ui.recipe.RecipeFragment;

import com.facebook.stetho.Stetho;




public class MainActivity extends AppCompatActivity  implements RecipeFragment.OnRecipeSelectedInterface  {

    public static final String RECIPE_FRAGMENT ="recipe_fragment";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stetho.initializeWithDefaults(this);

        setContentView(R.layout.main_activity);

        RecipeFragment savedFragment = (RecipeFragment) getSupportFragmentManager().findFragmentByTag(RECIPE_FRAGMENT);

        if (savedFragment == null) {

            RecipeFragment fragment = new RecipeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeholder,fragment,RECIPE_FRAGMENT);
            fragmentTransaction.commit();
        }




    }

    @Override
    public void onListRecipeSelected(int index,  Recipe recipeClicked) {


        Intent intentToStartRecipeDetailActivity = new Intent(this, RecipeDetailActivity.class);
        intentToStartRecipeDetailActivity.putExtra("Recipe.Details",recipeClicked);
        intentToStartRecipeDetailActivity.putExtra("Step.Details",recipeClicked.getRecipeSteps());
        this.startActivity(intentToStartRecipeDetailActivity);




    }





}
