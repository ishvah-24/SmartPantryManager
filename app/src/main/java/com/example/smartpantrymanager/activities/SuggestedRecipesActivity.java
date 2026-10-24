
package com.example.smartpantrymanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.IngredientMatcher;
import com.example.smartpantrymanager.R;
import com.example.smartpantrymanager.dao.PantryDAO;
import com.example.smartpantrymanager.dao.RecipeDAO;
import com.example.smartpantrymanager.dao.RecipeIngredientDAO;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.models.PantryItem;
import com.example.smartpantrymanager.models.Recipe;
import com.example.smartpantrymanager.models.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private ListView suggestedRecipesList;
    private TextView emptyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        suggestedRecipesList =
                findViewById(R.id.suggestedRecipesList);

        // Show a message when no recipes match
        emptyMessage = findViewById(R.id.emptyMessage);

        suggestedRecipesList.setEmptyView(emptyMessage);

        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        PantryDAO pantryDAO = new PantryDAO(dbHelper);
        RecipeDAO recipeDAO = new RecipeDAO(dbHelper);
        RecipeIngredientDAO ingredientDAO =
                new RecipeIngredientDAO(dbHelper);

        List<PantryItem> pantryItems = pantryDAO.getAll();
        List<Recipe> recipes = recipeDAO.getAll();

        List<Recipe> matchingRecipes = new ArrayList<>();
        List<String> recipeNames = new ArrayList<>();

        // Check every recipe against the pantry
        for (Recipe recipe : recipes) {

            List<RecipeIngredient> requiredIngredients =
                    ingredientDAO.getByRecipeId(
                            recipe.getRecipe_id()
                    );

            boolean canMake = IngredientMatcher.hasAllIngredients(
                    pantryItems,
                    requiredIngredients
            );

            // Only show recipes with every required ingredient
            if (canMake) {
                matchingRecipes.add(recipe);
                recipeNames.add(recipe.getName());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                recipeNames
        );

        suggestedRecipesList.setAdapter(adapter);

        // Open the selected recipe's details
        suggestedRecipesList.setOnItemClickListener(
                (parent, view, position, id) -> {

                    Recipe selectedRecipe =
                            matchingRecipes.get(position);

                    Intent intent = new Intent(
                            SuggestedRecipesActivity.this,
                            RecipeDetailActivity.class
                    );

                    intent.putExtra(
                            "recipe_id",
                            selectedRecipe.getRecipe_id()
                    );

                    startActivity(intent);
                }
        );
    }
}
