package com.jmricop.recipesapp.presenter;


import com.jmricop.recipesapp.api.RecipesApiClient;
import com.jmricop.recipesapp.api.RecipesApiInterface;
import com.jmricop.recipesapp.interactor.MainViewInteractor;
import com.jmricop.recipesapp.model.Recipes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewPresenter implements MainViewInteractor.MainPresenter {

    private MainViewInteractor.MainView mainView;


    @Override
    public void setVista(MainViewInteractor.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void searchRecipes(String query) {

        RecipesApiClient.apiService().create(RecipesApiInterface.class).searchRecipes(query).enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> recipesResponse) {
                if (recipesResponse.isSuccessful()) {
                    mainView.setRecipesListAdapter(recipesResponse.body().results);
                }
            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
            }
        });
    }
}
