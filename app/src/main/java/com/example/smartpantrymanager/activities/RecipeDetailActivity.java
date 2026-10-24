
package com.example.smartpantrymanager.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.R;
import com.example.smartpantrymanager.dao.RecipeDAO;
import com.example.smartpantrymanager.dao.RecipeIngredientDAO;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.models.Recipe;
import com.example.smartpantrymanager.models.RecipeIngredient;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView title = findViewById(R.id.recipeTitle);
        TextView ingredientsText = findViewById(R.id.recipeIngredients);
        TextView stepsText = findViewById(R.id.recipeSteps);

        int recipeId = getIntent().getIntExtra("recipe_id", -1);

        if (recipeId == -1) {
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        RecipeDAO recipeDAO = new RecipeDAO(dbHelper);
        RecipeIngredientDAO ingredientDAO = new RecipeIngredientDAO(dbHelper);

        Recipe selectedRecipe = null;

        for (Recipe recipe : recipeDAO.getAll()) {
            if (recipe.getRecipe_id() == recipeId) {
                selectedRecipe = recipe;
                break;
            }
        }

        if (selectedRecipe == null) {
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        title.setText(selectedRecipe.getName());
        stepsText.setText(selectedRecipe.getSteps());

        List<RecipeIngredient> ingredients =
                ingredientDAO.getByRecipeId(recipeId);

        StringBuilder text = new StringBuilder();

        for (RecipeIngredient ingredient : ingredients) {
            text.append("• ")
                    .append(ingredient.getName())
                    .append(" — ")
                    .append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append("\n");
        }

        ingredientsText.setText(text.toString());
    }
}
