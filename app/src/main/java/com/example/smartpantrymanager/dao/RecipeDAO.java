
package com.example.smartpantrymanager.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.models.Recipe;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    private final DatabaseHelper dbHelper;

    public RecipeDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public long insert(Recipe recipe) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", recipe.getName());
        values.put("steps", recipe.getSteps());

        return db.insert("recipes", null, values);
    }



    public List<Recipe> getAll() {

        List<Recipe> recipes = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "recipes",
                null,
                null,
                null,
                null,
                null,
                "name ASC"
        );

        while (cursor.moveToNext()) {

            Recipe recipe = new Recipe(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("steps"))
            );

            recipes.add(recipe);
        }

        cursor.close();

        return recipes;
    }


    public boolean hasRecipes() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM recipes",
                null
        );

        cursor.moveToFirst();

        int count = cursor.getInt(0);

        cursor.close();

        return count > 0;
    }



    public boolean existsByName(String name) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "recipes",
                new String[]{"id"},
                "name = ?",
                new String[]{name},
                null,
                null,
                null
        );

        boolean exists = cursor.moveToFirst();
        cursor.close();

        return exists;
    }


}
