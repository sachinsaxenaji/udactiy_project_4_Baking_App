package com.example.android.bakingapp.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.android.bakingapp.R;


import com.example.android.bakingapp.RecipeDetailActivity;
import com.example.android.bakingapp.StepDetailActivity;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;
import com.example.android.bakingapp.ui.recipe.RecipeStepDetailFragment;


import java.util.ArrayList;

public class StepsListAdapter extends RecyclerView.Adapter<StepsListAdapter.StepRecyclerViewHolder> {

    private ArrayList<Step> mSteps;

    private Recipe mRecipe;
    private Context mContext;

    private boolean mTwoPane;
    public static final String KEY_STEP_OBJ = "step_obj";
    public static final String KEY_RECIPE_OBJ = "recipe_obj";

    public StepsListAdapter(Context context, Recipe item) {
        this.mContext = context;
        this.mRecipe = item;
        this.mSteps = mRecipe.getRecipeSteps();
    }



    public class StepRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public final Button mStepButton;

        public StepRecyclerViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            mStepButton = view.findViewById(R.id.btn_step);

            mStepButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

             Step myStepSelected = mSteps.get(getAdapterPosition());

            if (((RecipeDetailActivity) mContext).findViewById(R.id.twoPaneLayout) != null) {
                mTwoPane = true;
            }

            if(mTwoPane){

                RecipeStepDetailFragment stepDetailFragment = new RecipeStepDetailFragment();

                Bundle args = new Bundle();
                args.putParcelable(KEY_STEP_OBJ, myStepSelected);
                args.putParcelable(KEY_RECIPE_OBJ, mRecipe);

                stepDetailFragment.setArguments(args);

                ((RecipeDetailActivity) mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.placeholder2, stepDetailFragment)
                        .commit();

            }else {
                Intent intentToStartStepDetailActivity = new Intent(mContext, StepDetailActivity.class);
                intentToStartStepDetailActivity.putExtra("Step.Details", myStepSelected);
                intentToStartStepDetailActivity.putExtra("Recipe.Info", mRecipe);
                mContext.startActivity(intentToStartStepDetailActivity);
            }



        }


    }



    @NonNull
    @Override
    public StepsListAdapter.StepRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.step_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View mView = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new StepsListAdapter.StepRecyclerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsListAdapter.StepRecyclerViewHolder stepRecyclerViewHolder, int position) {

        mSteps = mRecipe.getRecipeSteps();


        for(int i = 0; i < mSteps.size(); i++) {
            String stepShortName = mSteps.get(position).getStepShortDescription();
            Button button = stepRecyclerViewHolder.mStepButton;
            button.setText(stepShortName);
        }

    }

    @Override
    public int getItemCount() {
        if (null == mSteps) return 0;
        return mSteps.size();
    }

    public void setStepData (ArrayList<Step> stepData){
        mSteps = stepData;
        notifyDataSetChanged();
    }
}
