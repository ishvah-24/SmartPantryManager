
package com.example.smartpantrymanager.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartpantrymanager.database.DatabaseHelper;

public class RecipeIngredientDAO {

    private final DatabaseHelper dbHelper;

    public RecipeIngredientDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public long insert(int recipeId, String name,
                       double quantity, String unit) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("recipe_id", recipeId);
        values.put("name", name);
        values.put("quantity", quantity);
        values.put("unit", unit);

        return db.insert("recipe_ingredients", null, values);
    }


    public java.util.List<com.example.smartpantrymanager.models.RecipeIngredient>
    getByRecipeId(int recipeId) {

        java.util.List<com.example.smartpantrymanager.models.RecipeIngredient>
                ingredients = new java.util.ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        android.database.Cursor cursor = db.query(
                "recipe_ingredients",
                null,
                "recipe_id = ?",
                new String[]{String.valueOf(recipeId)},
                null,
                null,
                "id ASC"
        );

        while (cursor.moveToNext()) {

            com.example.smartpantrymanager.models.RecipeIngredient ingredient =
                    new com.example.smartpantrymanager.models.RecipeIngredient(
                            cursor.getInt(cursor.getColumnIndexOrThrow("recipe_id")),
                            cursor.getString(cursor.getColumnIndexOrThrow("name")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("quantity")),
                            cursor.getString(cursor.getColumnIndexOrThrow("unit"))
                    );

            ingredients.add(ingredient);
        }

        cursor.close();

        return ingredients;
    }


}
