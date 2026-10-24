
package com.example.smartpantrymanager;

import com.example.smartpantrymanager.dao.RecipeDAO;
import com.example.smartpantrymanager.dao.RecipeIngredientDAO;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.models.Recipe;

public class RecipeSeeder {

    public static void seed(DatabaseHelper dbHelper) {

        RecipeDAO recipeDAO = new RecipeDAO(dbHelper);
        RecipeIngredientDAO ingredientDAO =
                new RecipeIngredientDAO(dbHelper);

        if (recipeDAO.hasRecipes()) {
            return;
        }

        // Add a starter recipe
        Recipe recipe = new Recipe(
                0,
                "Scrambled Eggs",
                "1. Crack the eggs into a bowl.\n"
                        + "2. Beat the eggs.\n"
                        + "3. Cook in a pan until set."
        );

        int recipeId = (int) recipeDAO.insert(recipe);

        // Add ingredients only if the recipe was saved
        if (recipeId != -1) {
            ingredientDAO.insert(recipeId, "Eggs", 2, "pcs");
            ingredientDAO.insert(recipeId, "Milk", 30, "ml");
            ingredientDAO.insert(recipeId, "Butter", 10, "g");
        }
    }
}
