package com.jmricop.recipesapp.api;

import com.jmricop.recipesapp.model.Recipes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipesApiInterface {

    String API_BASE_URL = "http://www.recipepuppy.com/";

    @GET("/api/")
    Call<Recipes> searchRecipes(@Query(value = "q") String query);

}
