package com.example.smartpantrymanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.R;
import com.example.smartpantrymanager.adapters.PantryAdapter;
import com.example.smartpantrymanager.dao.PantryDAO;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.models.PantryItem;

import android.widget.Button;
import android.content.Intent;

import java.util.List;

public class PantryActivity extends AppCompatActivity {

    private RecyclerView pantryRecyclerView;
    private PantryAdapter pantryAdapter;
    private PantryDAO pantryDao;
    private Button addIngredientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        pantryRecyclerView = findViewById(R.id.pantryRecyclerView);
        addIngredientButton = findViewById(R.id.addIngredientButton);

        addIngredientButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        pantryRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        pantryDao = new PantryDAO(dbHelper);

        loadPantry();
    }

    private void loadPantry() {
        List<PantryItem> items = pantryDao.getAll();

        pantryAdapter = new PantryAdapter(items);
        pantryRecyclerView.setAdapter(pantryAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();

        loadPantry();
    }
}