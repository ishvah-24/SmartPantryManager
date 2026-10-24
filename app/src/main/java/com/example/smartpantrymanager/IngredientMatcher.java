package com.example.smartpantrymanager;

import android.util.Log;

import com.example.smartpantrymanager.models.PantryItem;
import com.example.smartpantrymanager.models.RecipeIngredient;

import java.util.List;
import java.util.Locale;

public class IngredientMatcher {

    private static final String TAG = "IngredientMatcher";

    public static boolean hasAllIngredients(
            List<PantryItem> pantryItems,
            List<RecipeIngredient> requiredIngredients) {

        // Make sure the recipe has ingredients to check
        if (pantryItems == null || requiredIngredients == null
                || requiredIngredients.isEmpty()) {
            Log.d(TAG, "No pantry items or required ingredients.");
            return false;
        }

        // DEBUG: Display everything currently in the pantry
        Log.d(TAG, "========== CHECKING RECIPE ==========");

        for (PantryItem item : pantryItems) {
            Log.d(TAG, "PANTRY: name=" + item.getName()
                    + ", quantity=" + item.getQuantity()
                    + ", unit=" + item.getUnit());
        }

        // Check every ingredient the recipe requires
        for (RecipeIngredient required : requiredIngredients) {

            if (required == null) {
                Log.d(TAG, "A required ingredient is null.");
                return false;
            }

            // DEBUG: Display the ingredient required by the recipe
            Log.d(TAG, "REQUIRED: name=" + required.getName()
                    + ", quantity=" + required.getQuantity()
                    + ", unit=" + required.getUnit());

            boolean found = false;

            // Compare the required ingredient against pantry items
            for (PantryItem pantry : pantryItems) {

                if (pantry == null) {
                    continue;
                }

                String pantryName = normalize(pantry.getName());
                String requiredName = normalize(required.getName());

                boolean sameName = pantryName.equals(requiredName);

                // Prevent null quantity crashes
                Double pantryQuantity = pantry.getQuantity();
                Float requiredQuantity = required.getQuantity();

                boolean enoughQuantity =
                        pantryQuantity != null
                                && requiredQuantity != null
                                && pantryQuantity >= requiredQuantity;

                // Prevent null unit crashes
                String pantryUnit = pantry.getUnit();
                String requiredUnit = required.getUnit();

                boolean sameUnit =
                        pantryUnit != null
                                && requiredUnit != null
                                && pantryUnit.trim().equalsIgnoreCase(
                                requiredUnit.trim());

                // DEBUG: Show the comparison results
                Log.d(TAG, "COMPARISON: pantry=" + pantryName
                        + ", required=" + requiredName
                        + ", sameName=" + sameName
                        + ", enoughQuantity=" + enoughQuantity
                        + ", sameUnit=" + sameUnit);

                // Ingredient matches only when all conditions pass
                if (sameName && enoughQuantity && sameUnit) {
                    found = true;
                    break;
                }
            }

            // If even one required ingredient is missing,
            // the recipe must not be suggested
            if (!found) {
                Log.d(TAG, "NO MATCH for: " + required.getName());
                return false;
            }
        }

        // Every required ingredient matched
        Log.d(TAG, "MATCH: All required ingredients are available.");
        return true;
    }

    // Normalise names to handle capitalisation and common plurals
    private static String normalize(String name) {

        if (name == null) {
            return "";
        }

        String normalized = name.trim().toLowerCase(Locale.ROOT);

        if (normalized.equals("eggs")) {
            return "egg";
        }

        if (normalized.equals("tomatoes")) {
            return "tomato";
        }

        if (normalized.equals("potatoes")) {
            return "potato";
        }

        if (normalized.equals("bananas")) {
            return "banana";
        }

        // Handle other simple plurals
        if (normalized.endsWith("s") && !normalized.endsWith("ss")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }

        return normalized;
    }
}