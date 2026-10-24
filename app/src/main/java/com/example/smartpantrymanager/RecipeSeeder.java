
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

        // Recipe 1: Scrambled Eggs
        if (!recipeDAO.existsByName("Scrambled Eggs")) {

            int recipeId = (int) recipeDAO.insert(
                    new Recipe(
                            0,
                            "Scrambled Eggs",
                            "1. Beat the eggs.\n"
                                    + "2. Add milk.\n"
                                    + "3. Cook with butter until set."
                    )
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Eggs", 2, "pcs");
                ingredientDAO.insert(recipeId, "Milk", 30, "ml");
                ingredientDAO.insert(recipeId, "Butter", 10, "g");
            }
        }

        // Recipe 2: Pancakes
        if (!recipeDAO.existsByName("Pancakes")) {

            int recipeId = (int) recipeDAO.insert(
                    new Recipe(
                            0,
                            "Pancakes",
                            "1. Mix the flour, milk and egg.\n"
                                    + "2. Pour batter into a pan.\n"
                                    + "3. Cook both sides until golden."
                    )
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Flour", 200, "g");
                ingredientDAO.insert(recipeId, "Milk", 250, "ml");
                ingredientDAO.insert(recipeId, "Eggs", 1, "pcs");
            }
        }

        // Recipe 3: Buttered Pasta
        if (!recipeDAO.existsByName("Buttered Pasta")) {

            int recipeId = (int) recipeDAO.insert(
                    new Recipe(
                            0,
                            "Buttered Pasta",
                            "1. Boil the pasta until tender.\n"
                                    + "2. Drain the water.\n"
                                    + "3. Stir in the butter."
                    )
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Pasta", 200, "g");
                ingredientDAO.insert(recipeId, "Butter", 15, "g");
            }
        }


        // Recipe 4: Cheese Omelette
        if (!recipeDAO.existsByName("Cheese Omelette")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Cheese Omelette",
                            "1. Beat the eggs.\n"
                                    + "2. Pour into a heated pan.\n"
                                    + "3. Add cheese and fold when cooked.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Eggs", 2, "pcs");
                ingredientDAO.insert(recipeId, "Cheese", 30, "g");
                ingredientDAO.insert(recipeId, "Butter", 5, "g");
            }
        }

        // Recipe 5: French Toast
        if (!recipeDAO.existsByName("French Toast")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "French Toast",
                            "1. Beat the egg and milk together.\n"
                                    + "2. Dip the bread in the mixture.\n"
                                    + "3. Fry until golden on both sides.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Bread", 2, "slices");
                ingredientDAO.insert(recipeId, "Eggs", 1, "pcs");
                ingredientDAO.insert(recipeId, "Milk", 30, "ml");
                ingredientDAO.insert(recipeId, "Butter", 5, "g");
            }
        }

        // Recipe 6: Garlic Butter Pasta
        if (!recipeDAO.existsByName("Garlic Butter Pasta")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Garlic Butter Pasta",
                            "1. Boil the pasta.\n"
                                    + "2. Melt butter and cook the garlic.\n"
                                    + "3. Mix the pasta with the garlic butter.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Pasta", 200, "g");
                ingredientDAO.insert(recipeId, "Butter", 15, "g");
                ingredientDAO.insert(recipeId, "Garlic", 2, "cloves");
            }
        }

        // Recipe 7: Banana Pancakes
        if (!recipeDAO.existsByName("Banana Pancakes")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Banana Pancakes",
                            "1. Mash the banana.\n"
                                    + "2. Mix with eggs and flour.\n"
                                    + "3. Cook small pancakes in a pan.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Banana", 1, "pcs");
                ingredientDAO.insert(recipeId, "Eggs", 2, "pcs");
                ingredientDAO.insert(recipeId, "Flour", 100, "g");
            }
        }

        // Recipe 8: Tomato Pasta
        if (!recipeDAO.existsByName("Tomato Pasta")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Tomato Pasta",
                            "1. Boil the pasta.\n"
                                    + "2. Cook the chopped tomatoes.\n"
                                    + "3. Mix the sauce with the pasta.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Pasta", 200, "g");
                ingredientDAO.insert(recipeId, "Tomatoes", 2, "pcs");
                ingredientDAO.insert(recipeId, "Garlic", 1, "cloves");
            }
        }


        // Recipe 9: Scrambled Eggs on Toast
        if (!recipeDAO.existsByName("Scrambled Eggs on Toast")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Scrambled Eggs on Toast",
                            "1. Scramble the eggs in a pan.\n"
                                    + "2. Toast the bread.\n"
                                    + "3. Serve the eggs on the toast.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Eggs", 2, "pcs");
                ingredientDAO.insert(recipeId, "Bread", 2, "slices");
                ingredientDAO.insert(recipeId, "Butter", 5, "g");
            }
        }

        // Recipe 10: Tomato and Cheese Omelette
        if (!recipeDAO.existsByName("Tomato and Cheese Omelette")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Tomato and Cheese Omelette",
                            "1. Beat the eggs.\n"
                                    + "2. Add chopped tomatoes and cheese.\n"
                                    + "3. Cook in a pan until set.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Eggs", 2, "pcs");
                ingredientDAO.insert(recipeId, "Tomatoes", 1, "pcs");
                ingredientDAO.insert(recipeId, "Cheese", 30, "g");
                ingredientDAO.insert(recipeId, "Butter", 5, "g");
            }
        }

        // Recipe 11: Banana Milkshake
        if (!recipeDAO.existsByName("Banana Milkshake")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Banana Milkshake",
                            "1. Peel the banana.\n"
                                    + "2. Blend with the milk.\n"
                                    + "3. Serve chilled.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Banana", 1, "pcs");
                ingredientDAO.insert(recipeId, "Milk", 250, "ml");
            }
        }

        // Recipe 12: Garlic Bread
        if (!recipeDAO.existsByName("Garlic Bread")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Garlic Bread",
                            "1. Mix softened butter and crushed garlic.\n"
                                    + "2. Spread over bread.\n"
                                    + "3. Toast until golden.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Bread", 2, "slices");
                ingredientDAO.insert(recipeId, "Butter", 15, "g");
                ingredientDAO.insert(recipeId, "Garlic", 1, "cloves");
            }
        }

        // Recipe 13: Tomato and Cheese Toast
        if (!recipeDAO.existsByName("Tomato and Cheese Toast")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Tomato and Cheese Toast",
                            "1. Place sliced tomatoes on the bread.\n"
                                    + "2. Add cheese.\n"
                                    + "3. Toast until the cheese melts.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Bread", 2, "slices");
                ingredientDAO.insert(recipeId, "Tomatoes", 1, "pcs");
                ingredientDAO.insert(recipeId, "Cheese", 30, "g");
            }
        }


        // Recipe 14: Egg Fried Rice
        if (!recipeDAO.existsByName("Egg Fried Rice")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Egg Fried Rice",
                            "1. Cook the rice.\n"
                                    + "2. Scramble the eggs in a pan.\n"
                                    + "3. Add rice and soy sauce, then stir-fry.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Rice", 200, "g");
                ingredientDAO.insert(recipeId, "Eggs", 2, "pcs");
                ingredientDAO.insert(recipeId, "Soy Sauce", 15, "ml");
            }
        }

        // Recipe 15: Mashed Potatoes
        if (!recipeDAO.existsByName("Mashed Potatoes")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Mashed Potatoes",
                            "1. Peel and boil the potatoes until soft.\n"
                                    + "2. Drain and mash them.\n"
                                    + "3. Mix in the butter and milk.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Potatoes", 300, "g");
                ingredientDAO.insert(recipeId, "Butter", 15, "g");
                ingredientDAO.insert(recipeId, "Milk", 50, "ml");
            }
        }

        // Recipe 16: Cheese Pasta
        if (!recipeDAO.existsByName("Cheese Pasta")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Cheese Pasta",
                            "1. Boil the pasta until tender.\n"
                                    + "2. Add the cheese and milk.\n"
                                    + "3. Stir until creamy.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Pasta", 200, "g");
                ingredientDAO.insert(recipeId, "Cheese", 50, "g");
                ingredientDAO.insert(recipeId, "Milk", 100, "ml");
            }
        }

        // Recipe 17: Banana Toast
        if (!recipeDAO.existsByName("Banana Toast")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Banana Toast",
                            "1. Toast the bread.\n"
                                    + "2. Slice the banana.\n"
                                    + "3. Place the banana slices on the toast.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Bread", 2, "slices");
                ingredientDAO.insert(recipeId, "Banana", 1, "pcs");
            }
        }

        // Recipe 18: Buttered Rice
        if (!recipeDAO.existsByName("Buttered Rice")) {
            int recipeId = (int) recipeDAO.insert(
                    new Recipe(0, "Buttered Rice",
                            "1. Rinse the rice.\n"
                                    + "2. Boil it in water until tender.\n"
                                    + "3. Drain if needed and stir in butter.")
            );

            if (recipeId != -1) {
                ingredientDAO.insert(recipeId, "Rice", 200, "g");
                ingredientDAO.insert(recipeId, "Butter", 10, "g");
            }
        }



    }
}
