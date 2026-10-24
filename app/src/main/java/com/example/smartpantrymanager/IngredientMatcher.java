
package com.example.smartpantrymanager;

import com.example.smartpantrymanager.models.PantryItem;
import com.example.smartpantrymanager.models.RecipeIngredient;

import java.util.List;
import java.util.Locale;

public class IngredientMatcher {

    public static boolean hasAllIngredients(
            List<PantryItem> pantryItems,
            List<RecipeIngredient> requiredIngredients) {

        for (RecipeIngredient required : requiredIngredients) {

            boolean found = false;

            for (PantryItem pantry : pantryItems) {

                String pantryName = normalize(pantry.getName());
                String requiredName = normalize(required.getName());

                boolean sameName = pantryName.equals(requiredName);
                boolean enoughQuantity =
                        pantry.getQuantity() >= required.getQuantity();
                boolean sameUnit =
                        pantry.getUnit().equalsIgnoreCase(required.getUnit());

                if (sameName && enoughQuantity && sameUnit) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return !requiredIngredients.isEmpty();
    }

    private static String normalize(String name) {
        return name.trim().toLowerCase(Locale.ROOT);
    }
}
