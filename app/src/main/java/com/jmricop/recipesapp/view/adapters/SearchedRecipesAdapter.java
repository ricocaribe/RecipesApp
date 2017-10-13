package com.jmricop.recipesapp.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmricop.recipesapp.R;
import com.jmricop.recipesapp.interactor.MainViewInteractor;
import com.jmricop.recipesapp.model.Recipes;
import com.squareup.picasso.Picasso;
import butterknife.ButterKnife;
import butterknife.InjectView;


public class SearchedRecipesAdapter extends RecyclerView.Adapter<SearchedRecipesAdapter.ViewHolder> {

    private Recipes.Recipe[] recipes;
    private MainViewInteractor.MainView mainView;

    public SearchedRecipesAdapter(MainViewInteractor.MainView mainView) {
        this.mainView = mainView;
    }

    public void setSearchedRecipes(Recipes.Recipe[] recipes) {
        this.recipes = recipes;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.recipeTitle)
        TextView recipeTitle;
        @InjectView(R.id.recipeIngredients)
        TextView recipeIngredients;
        @InjectView(R.id.recipeHref)
        TextView recipeHref;
        @InjectView(R.id.recipeThumbnail)
        ImageView recipeThumbnail;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


    @Override
    public SearchedRecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_searched_recipe, parent, false);
        return new SearchedRecipesAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final SearchedRecipesAdapter.ViewHolder holder, final int position) {

        holder.recipeTitle.setText(recipes[position].title);
        holder.recipeIngredients.setText(recipes[position].ingredients);
        holder.recipeHref.setText(recipes[position].href);

        String thumbnail = recipes[position].thumbnail;

        if(!thumbnail.isEmpty()){
            Picasso.with(holder.itemView.getContext())
                    .load(thumbnail)
                    .fit().centerCrop()
                    .into(holder.recipeThumbnail);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainView.showDetailFragment(recipes[holder.getAdapterPosition()], holder.recipeThumbnail);
            }
        });
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return recipes.length;
    }
}