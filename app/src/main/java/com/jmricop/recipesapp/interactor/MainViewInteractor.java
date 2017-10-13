package com.jmricop.recipesapp.interactor;


import android.widget.ImageView;

import com.jmricop.recipesapp.model.Recipes;

public interface MainViewInteractor {

    interface MainView {
        void showAlert(String message);
        void setRecipesListAdapter(Recipes.Recipe[] recipes);
        void showDetailFragment(Recipes.Recipe recipe, ImageView recipeImage);
    }

    interface MainPresenter {
        void setVista(MainView mainView);
        void searchRecipes(String query);
    }
}
