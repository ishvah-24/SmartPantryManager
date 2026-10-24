
package com.example.smartpantrymanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.R;
import com.example.smartpantrymanager.adapters.PantryAdapter;
import com.example.smartpantrymanager.dao.PantryDAO;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.models.PantryItem;
import com.example.smartpantrymanager.RecipeSeeder;

import java.util.List;

import com.example.smartpantrymanager.activities.AddEditIngredientActivity;

public class PantryActivity extends AppCompatActivity {

    private RecyclerView pantryRecyclerView;
    private PantryAdapter pantryAdapter;
    private PantryDAO pantryDao;

    private Button addIngredientButton;
    private Button suggestedRecipesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        // Connect the buttons
        pantryRecyclerView = findViewById(R.id.pantryRecyclerView);
        addIngredientButton = findViewById(R.id.addIngredientButton);
        suggestedRecipesButton = findViewById(R.id.suggestedRecipesButton);

        // Set up the pantry list
        pantryRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // Connect to the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        pantryDao = new PantryDAO(dbHelper);

        // Add recipes to the database if they do not already exist
        RecipeSeeder.seed(dbHelper);

        // Open the Add Ingredient screen
        addIngredientButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    PantryActivity.this,
                    AddEditIngredientActivity.class
            );
            startActivity(intent);
        });

        // Open the Suggested Recipes screen
        suggestedRecipesButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    PantryActivity.this,
                    SuggestedRecipesActivity.class
            );
            startActivity(intent);
        });

        loadPantry();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh pantry items when returning to this screen
        if (pantryDao != null && pantryRecyclerView != null) {
            loadPantry();
        }
    }

    private void loadPantry() {
        List<PantryItem> items = pantryDao.getAll();

        pantryAdapter = new PantryAdapter(items);
        pantryRecyclerView.setAdapter(pantryAdapter);
    }
}
