
package com.example.smartpantrymanager.activities;

import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private final int darkGreen = Color.rgb(36, 74, 53);
    private final int lightGreen = Color.rgb(245, 248, 242);
    private final int textGreen = Color.rgb(101, 117, 104);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Main screen layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(28, 55, 28, 28);
        layout.setBackgroundColor(lightGreen);

        // Screen title
        TextView title = new TextView(this);
        title.setText("Settings");
        title.setTextSize(28);
        title.setTextColor(darkGreen);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, 28);

        // About section heading
        TextView aboutTitle = new TextView(this);
        aboutTitle.setText("About the App");
        aboutTitle.setTextSize(20);
        aboutTitle.setTextColor(darkGreen);
        aboutTitle.setTypeface(null, Typeface.BOLD);

        // About section description
        TextView aboutDescription = new TextView(this);
        aboutDescription.setText(
                "Smart Pantry Manager helps you manage pantry " +
                        "ingredients and reduce food waste."
        );
        aboutDescription.setTextSize(16);
        aboutDescription.setTextColor(textGreen);
        aboutDescription.setPadding(0, 10, 0, 28);

        // Recipe matching heading
        TextView recipeTitle = new TextView(this);
        recipeTitle.setText("Recipe Matching");
        recipeTitle.setTextSize(20);
        recipeTitle.setTextColor(darkGreen);
        recipeTitle.setTypeface(null, Typeface.BOLD);

        // Recipe matching description
        TextView recipeDescription = new TextView(this);
        recipeDescription.setText(
                "Recipes are suggested only when you have every " +
                        "required ingredient in sufficient quantity and " +
                        "with the correct unit."
        );
        recipeDescription.setTextSize(16);
        recipeDescription.setTextColor(textGreen);
        recipeDescription.setPadding(0, 10, 0, 30);

        // Back button
        Button backButton = new Button(this);
        backButton.setText("Back to Home");
        backButton.setTextSize(16);
        backButton.setAllCaps(false);
        backButton.setTextColor(Color.WHITE);
        backButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        Color.rgb(53, 107, 73)
                )
        );

        backButton.setOnClickListener(v -> finish());

        // Add views to the screen
        layout.addView(title);
        layout.addView(aboutTitle);
        layout.addView(aboutDescription);
        layout.addView(recipeTitle);
        layout.addView(recipeDescription);
        layout.addView(backButton);

        setContentView(layout);
    }
}
