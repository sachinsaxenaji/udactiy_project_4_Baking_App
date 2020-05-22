package com.example.android.bakingapp;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.widget.Toast;


import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;
import com.example.android.bakingapp.ui.recipe.RecipeDetailFragment;
import com.example.android.bakingapp.ui.recipe.RecipeStepDetailFragment;





public class RecipeDetailActivity extends AppCompatActivity {
    public static final String RECIPE_DETAIL_FRAGMENT ="ingredients_steps_fragment";
    public static final String STEP_FRAGMENT ="step_fragment";
    private Recipe mRecipeObj;
    private Step mStepObj;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail_activity);

        if (findViewById(R.id.twoPaneLayout) != null) {
            mTwoPane = true;
        }

        final Intent intent = getIntent();
        if (intent == null) {
            closeOnError();

        }
        try {

            mRecipeObj = intent.getParcelableExtra("Recipe.Details");
            if(mTwoPane) {
                mStepObj = intent.getParcelableExtra("Step.Details");
            }
            this.setTitle(mRecipeObj.getRecipeName());





        }catch (Exception e){

            e.printStackTrace();
        }




        FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                if(mTwoPane){


                    RecipeDetailFragment savedFragment = (RecipeDetailFragment) getSupportFragmentManager().findFragmentByTag(RECIPE_DETAIL_FRAGMENT);
                    if (savedFragment == null) {
                        RecipeDetailFragment fragment = new RecipeDetailFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(RecipeDetailFragment.KEY_RECIPE_OBJ, mRecipeObj);
                        bundle.putParcelable(RecipeDetailFragment.KEY_STEP_OBJ, mStepObj);
                        fragment.setArguments(bundle);
                        fragmentTransaction.add(R.id.placeholder1, fragment);
                        Step mFirstStep = mRecipeObj.getRecipeSteps().get(0);
                        RecipeStepDetailFragment fagment2 = RecipeStepDetailFragment.newInstance(mRecipeObj, mFirstStep);
                        fragmentTransaction.add(R.id.placeholder2, fagment2);
                        fragmentTransaction.commit();
                    }

                }else {
                    RecipeDetailFragment savedFragment = (RecipeDetailFragment) getSupportFragmentManager().findFragmentByTag(RECIPE_DETAIL_FRAGMENT);
                    if (savedFragment == null) {
                        RecipeDetailFragment fragment = new RecipeDetailFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(RecipeDetailFragment.KEY_RECIPE_OBJ, mRecipeObj);
                        fragment.setArguments(bundle);
                        fragmentTransaction.add(R.id.placeholder1, fragment, RECIPE_DETAIL_FRAGMENT);
                        fragmentTransaction.commit();
                    }
            }




    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }



}
