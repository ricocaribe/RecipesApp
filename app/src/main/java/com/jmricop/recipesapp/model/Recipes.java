package com.jmricop.recipesapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Recipes implements Serializable {

    @SerializedName("results") public Recipe[] results;

    public class Recipe implements Serializable {

        @SerializedName("title") public String title;

        @SerializedName("href") public String href;

        @SerializedName("ingredients") public String ingredients;

        @SerializedName("thumbnail") public String thumbnail;

    }
}
