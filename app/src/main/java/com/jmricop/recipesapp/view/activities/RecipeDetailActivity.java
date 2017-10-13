package com.jmricop.recipesapp.view.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmricop.recipesapp.R;
import com.jmricop.recipesapp.model.Recipes;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RecipeDetailActivity extends AppCompatActivity {

    @InjectView(R.id.recipeDetailThumbnail)
    ImageView recipeDetailThumbnail;

    @InjectView(R.id.recipeDetailTitle)
    TextView recipeDetailTitle;

    @InjectView(R.id.recipeDetailIngredients)
    TextView recipeDetailIngredients;

    @InjectView(R.id.recipeDetailHref)
    TextView recipeDetailHref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) getSupportActionBar().setHomeButtonEnabled(true);

        Recipes.Recipe recipe = (Recipes.Recipe) getIntent().getSerializableExtra("recipe");

        String thumbnail = recipe.thumbnail;

        if(!thumbnail.isEmpty()){

            supportPostponeEnterTransition();

            Picasso.with(getApplicationContext())
                    .load(thumbnail)
                    .fit().centerCrop()
                    .noFade()
                    .into(recipeDetailThumbnail, new Callback() {
                        @Override
                        public void onSuccess() {
                            supportStartPostponedEnterTransition();
                        }

                        @Override
                        public void onError() {
                            supportStartPostponedEnterTransition();
                        }
                    });
        }

        recipeDetailTitle.setText(recipe.title);
        recipeDetailIngredients.setText(recipe.ingredients);
        recipeDetailHref.setText(recipe.href);
    }
}
