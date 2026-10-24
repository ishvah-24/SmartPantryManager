
package com.example.smartpantrymanager.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        suggestedRecipesList =
                findViewById(R.id.suggestedRecipesList);

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

        for (Recipe recipe : recipes) {

            List<RecipeIngredient> requiredIngredients =
                    ingredientDAO.getByRecipeId(recipe.getRecipe_id());

            boolean canMake = IngredientMatcher.hasAllIngredients(
                    pantryItems,
                    requiredIngredients
            );

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


        suggestedRecipesList.setOnItemClickListener((parent, view, position, id) -> {

            Recipe selectedRecipe = matchingRecipes.get(position);

            Intent intent = new Intent(
                    SuggestedRecipesActivity.this,
                    RecipeDetailActivity.class
            );

            intent.putExtra("recipe_id", selectedRecipe.getRecipe_id());

            startActivity(intent);
        });


        if (matchingRecipes.isEmpty()) {
            Toast.makeText(
                    this,
                    "No recipes match your current pantry.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
