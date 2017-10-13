package com.jmricop.recipesapp.presenter;


import com.jmricop.recipesapp.R;
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

        int DEFAULT_PAGINATION = 10;

        RecipesApiClient.apiService().create(RecipesApiInterface.class).searchRecipes(query, DEFAULT_PAGINATION).enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> recipesResponse) {

                if (recipesResponse.isSuccessful()) {
                    mainView.setRecipesListAdapter(recipesResponse.body().results);
                }
                else mainView.showAlert(recipesResponse.message());

            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
                //mainView.showAlert(mainView.getContext().getResources().getString(R.string.txt_error_something_wrong));
                call.cancel();
                t.printStackTrace();
            }
        });
    }
}
