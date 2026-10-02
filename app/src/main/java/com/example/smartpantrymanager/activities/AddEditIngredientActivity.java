package com.example.smartpantrymanager.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.R;
import com.example.smartpantrymanager.dao.PantryDao;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.models.PantryItem;

import java.util.Calendar;

public class AddEditIngredientActivity extends AppCompatActivity{

    private EditText nameEditText;
    private EditText quantityEditText;
    private EditText unitEditText;
    private EditText expiryDateEditText;
    private Button saveButton;

    private PantryDao pantryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_edit_ingredient);

        nameEditText = findViewById(R.id.nameEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        unitEditText = findViewById(R.id.unitEditText);
        expiryDateEditText = findViewById(R.id.expiryDateEditText);
        saveButton = findViewById(R.id.saveButton);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        //Using the pantryDAO object, it accesses the DB helper to access the pantry specific table in the database
        pantryDao = new PantryDao(dbHelper);

        expiryDateEditText.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view1, selectedYear, selectedMonth, selectedDay) -> {

                        selectedMonth++;

                        String selectedDate;
                        selectedDate = String.format(
                                "%04d-%02d-%02d",
                                selectedYear,
                                selectedMonth,
                                selectedDay
                        );

                        expiryDateEditText.setText(selectedDate);
                    },
                    year,
                    month,
                    day
            );

            datePickerDialog.show();
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String name = nameEditText.getText().toString();
                String quantityText = quantityEditText.getText().toString();
                String unit = unitEditText.getText().toString();
                String expiryDate = expiryDateEditText.getText().toString();

                Double quantity;

                //input validation for when a user enters an ingredient through the Add/Edit screen
                if(name.isEmpty()) {
                    nameEditText.setError("No ingredient entered, please enter ingredient name");
                    return;
                }else if(quantityText.isEmpty()) {
                    quantityEditText.setError("Please enter a quantity");
                    return;

                }

                try {
                    quantity = Double.parseDouble(quantityText);

                    if(quantity <=  0){
                        quantityEditText.setError("Please enter a quantity greater than 0");
                        return;
                    }
                } catch (NumberFormatException e) {
                    quantityEditText.setError("No quantity entered, please enter ingredient quantity");
                    return;
                }

                if(unit.isEmpty()){
                    unitEditText.setError("No quantity unit entered, please enter unit of measurement");
                    return;
                }

                String normalizedName = name.trim().toLowerCase();

                PantryItem pantryItem = new PantryItem(
                        name,
                        normalizedName,
                        quantity,
                        unit,
                        expiryDate
                );

                long result = pantryDao.insert(pantryItem);

                if (result != -1) {
                    Toast.makeText(AddEditIngredientActivity.this, "Ingredient saved", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddEditIngredientActivity.this, "Failed to save ingredient", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
