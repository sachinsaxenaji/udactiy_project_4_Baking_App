package com.example.android.bakingapp.ui.recipe;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.content.Context;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import com.example.android.bakingapp.R;


import com.example.android.bakingapp.database.FavoriteEntry;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Recipe;


import com.example.android.bakingapp.utils.IngredientsListAdapter;
import com.example.android.bakingapp.utils.StepsListAdapter;


import java.util.List;


public class RecipeDetailFragment extends Fragment {




    public static final String KEY_RECIPE_OBJ = "recipe_obj";
    public static final String KEY_STEP_OBJ = "step_obj";

    private IngredientsListAdapter mIgredientListAdapter;
    private StepsListAdapter mStepListAdapter;
    private Recipe mRecipeClicked;

    private RecipeViewModel recipeViewModel;
    private Context context;
    private ImageView mIvToggle;

    String favAllIngredients;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {




       mRecipeClicked = getArguments().getParcelable(KEY_RECIPE_OBJ);

        getActivity().setTitle(mRecipeClicked.getRecipeName());




       View view = inflater.inflate(R.layout.ingredients_steps_fragment,container,false);


       RecyclerView recyclerViewIngredients = view.findViewById(R.id.rv_ingredients);
       mIgredientListAdapter = new IngredientsListAdapter(getContext(),mRecipeClicked);
       recyclerViewIngredients.setAdapter(mIgredientListAdapter);

       LinearLayoutManager layoutManager
               = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
       recyclerViewIngredients.setLayoutManager(layoutManager);
       mIgredientListAdapter.setIngredientsData(mRecipeClicked.getRecipeIngredients());


       RecyclerView recyclerViewSteps = view.findViewById(R.id.rv_steps);
       mStepListAdapter = new StepsListAdapter(getContext(),mRecipeClicked);
       recyclerViewSteps.setAdapter(mStepListAdapter);
       LinearLayoutManager layoutManagerSteps
               = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
       recyclerViewSteps.setLayoutManager(layoutManagerSteps);
         mStepListAdapter.setStepData(mRecipeClicked.getRecipeSteps());


        final RecipeViewModel mRecipesViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        final String favName = mRecipeClicked.getRecipeName();

        final List<Ingredient> mIngredients = mRecipeClicked.getRecipeIngredients();

        LiveData<List<FavoriteEntry>> mAllFavorites = mRecipesViewModel.getAllFavorites();

        mAllFavorites.observe(this,
                new Observer<List<FavoriteEntry>>() {
                    @Override
                    public void onChanged(@Nullable final List<FavoriteEntry> favoriteMovieEntries) {
                        if (favoriteMovieEntries != null) {
                            if (favoriteMovieEntries.size() > 0 ) {
                                for (FavoriteEntry temp : favoriteMovieEntries) {
                                    String favoriteName = temp.getRecipeName();
                                    if(favoriteName.equals(favName)){
                                        mIvToggle.setActivated(true);
                                    }
                                }
                            }
                        }

                    }
                });

        mIvToggle =  view.findViewById(R.id.iv_toggle);
        mIvToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mIvToggle.isActivated()){
                    mIvToggle.setActivated(true);

                    for(int i=0; i<mIngredients.size(); i++) {
                        Ingredient currentX = mIngredients.get(i);

                        if(favAllIngredients!= null) {
                            favAllIngredients = favAllIngredients + "\n" + currentX.getIngredientName() + " " + currentX.getIngredientUnit() + " (" + currentX.getIngredientQuantity() + ")";
                        }else{
                            favAllIngredients = currentX.getIngredientName() + " " + currentX.getIngredientUnit() + " (" + currentX.getIngredientQuantity() + ")";
                        }

                    }

                    favAllIngredients = favName  + "\n" + favAllIngredients;

                    FavoriteEntry favorite = new FavoriteEntry(favName,favAllIngredients);
                    mRecipesViewModel.insertFavorite(favorite);


                }else{

                    mIvToggle.setActivated(false);
                    mRecipesViewModel.deleteFavorite(favName);


                }
            }
        });
        return view;
    }



    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(mRecipeClicked.getRecipeName());
    }







}
