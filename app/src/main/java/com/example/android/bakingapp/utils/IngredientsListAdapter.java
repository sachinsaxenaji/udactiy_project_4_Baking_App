package com.example.android.bakingapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Recipe;


import java.util.ArrayList;

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.IngredientRecyclerViewHolder> {

    private Recipe mRecipe;
    private Context mContext;
    private ArrayList<Ingredient> mIngredients;

    public IngredientsListAdapter(Context context, Recipe item) {
        this.mContext = context;
        this.mRecipe = item;
        this.mIngredients = mRecipe.getRecipeIngredients();
    }


    public class IngredientRecyclerViewHolder extends RecyclerView.ViewHolder   {

        private final TextView mIngredientTextView;
        private int mIndex;
        private Ingredient ingredient;


        public IngredientRecyclerViewHolder(View view) {
            super(view);

            mIngredientTextView = view.findViewById(R.id.tv_ingredientItemText);
        }


    }



    @NonNull
    @Override
    public  IngredientsListAdapter.IngredientRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.ingredient_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View mView = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new IngredientsListAdapter.IngredientRecyclerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsListAdapter.IngredientRecyclerViewHolder holder, int position) {
         mIngredients = mRecipe.getRecipeIngredients();

            TextView textViewIngredient = holder.mIngredientTextView;
            String ingredientsName = mIngredients.get(position).getIngredientName();
            String ingredientsQty = mIngredients.get(position).getIngredientQuantity();
            String ingredientsUnit = mIngredients.get(position).getIngredientUnit();
            textViewIngredient.setText(ingredientsName+" ("+ingredientsQty+" "+ingredientsUnit+")");


    }

    @Override
    public int getItemCount() {
        if (null == mIngredients) return 0;
        return mIngredients.size();
    }

    public void setIngredientsData (ArrayList<Ingredient> ingredientData){
        mIngredients = ingredientData;
        notifyDataSetChanged();
    }




}
