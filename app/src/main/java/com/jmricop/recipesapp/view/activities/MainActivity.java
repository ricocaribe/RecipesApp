package com.jmricop.recipesapp.view.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.ImageView;

import com.jmricop.recipesapp.R;
import com.jmricop.recipesapp.interactor.MainViewInteractor;
import com.jmricop.recipesapp.model.Recipes;
import com.jmricop.recipesapp.module.MainModule;
import com.jmricop.recipesapp.view.adapters.SearchedRecipesAdapter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dagger.ObjectGraph;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MainViewInteractor.MainView{


    @Inject
    MainViewInteractor.MainPresenter mainPresenter;

    @InjectView(R.id.rvSearchedRecipes)
    RecyclerView rvSearchedRecipes;

    private SearchedRecipesAdapter searchedRecipesAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObjectGraph objectGraph = ObjectGraph.create(new MainModule());
        objectGraph.inject(this);

        mainPresenter.setVista(this);

        ButterKnife.inject(this);

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        rvSearchedRecipes.setLayoutManager(gridLayoutManager);

        searchedRecipesAdapter = new SearchedRecipesAdapter(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search, menu);
        setupSearchView(menu);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        mainPresenter.searchRecipes(query);
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        if(!newText.isEmpty()) mainPresenter.searchRecipes(newText);
        return false;
    }


    private void setupSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(this);
    }


    @Override
    public void setRecipesListAdapter(Recipes.Recipe[] recipes) {
        searchedRecipesAdapter.setSearchedRecipes(recipes);
        rvSearchedRecipes.setAdapter(searchedRecipesAdapter);
    }


    @Override
    public void showDetailFragment(Recipes.Recipe recipe, ImageView recipeImage){
        searchView.clearFocus();
        Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
        intent.putExtra("recipe", recipe);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(MainActivity.this,
                        recipeImage,
                        ViewCompat.getTransitionName(recipeImage));

        startActivity(intent, options.toBundle());
    }
}
