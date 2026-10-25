
package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.activities.PantryActivity;
import com.example.smartpantrymanager.activities.SuggestedRecipesActivity;
import com.example.smartpantrymanager.activities.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private final int backgroundColor = Color.rgb(245, 248, 242);
    private final int darkGreen = Color.rgb(36, 74, 53);
    private final int mutedGreen = Color.rgb(101, 117, 104);
    private final int borderColor = Color.rgb(220, 229, 218);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Main screen
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(28, 60, 28, 20);
        layout.setBackgroundColor(backgroundColor);

        // Small app label
        TextView appLabel = new TextView(this);
        appLabel.setText("SMART PANTRY");
        appLabel.setTextSize(12);
        appLabel.setTextColor(mutedGreen);
        appLabel.setLetterSpacing(0.12f);

        // Main heading
        TextView title = new TextView(this);
        title.setText("Good food starts here.");
        title.setTextSize(27);
        title.setTextColor(darkGreen);
        title.setTypeface(null, Typeface.BOLD);
        title.setPadding(0, 12, 0, 8);

        // Subtitle
        TextView subtitle = new TextView(this);
        subtitle.setText(
                "Manage your ingredients. Discover what you can make."
        );
        subtitle.setTextSize(15);
        subtitle.setTextColor(mutedGreen);
        subtitle.setPadding(0, 0, 0, 32);

        // Pantry card
        LinearLayout pantryCard = createCard(
                "My Pantry",
                "View and manage your ingredients."
        );

        pantryCard.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, PantryActivity.class
            );
            startActivity(intent);
        });

        // Recipes card
        LinearLayout recipesCard = createCard(
                "Find Recipes",
                "Discover recipes using what you have."
        );

        recipesCard.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SuggestedRecipesActivity.class
            );
            startActivity(intent);
        });

        // Small settings button
        Button settingsButton = new Button(this);
        settingsButton.setText("Settings");
        settingsButton.setTextSize(14);
        settingsButton.setTextColor(mutedGreen);
        settingsButton.setAllCaps(false);
        settingsButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        backgroundColor
                )
        );
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, SettingsActivity.class
            );
            startActivity(intent);
        });

        // Add views to the screen
        layout.addView(appLabel);
        layout.addView(title);
        layout.addView(subtitle);
        layout.addView(pantryCard);
        layout.addView(recipesCard);

        // Keep settings near the bottom
        View spacer = new View(this);
        layout.addView(spacer, new LinearLayout.LayoutParams(
                1, 0, 1
        ));

        layout.addView(settingsButton);

        setContentView(layout);
    }

    private LinearLayout createCard(
            String heading,
            String description
    ) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(20, 20, 20, 20);
        card.setGravity(Gravity.CENTER_VERTICAL);

        // White background with rounded corners
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(Color.WHITE);
        shape.setCornerRadius(24);
        shape.setStroke(1, borderColor);
        card.setBackground(shape);

        // Card heading
        TextView title = new TextView(this);
        title.setText(heading);
        title.setTextSize(19);
        title.setTextColor(darkGreen);
        title.setTypeface(null, Typeface.BOLD);

        // Card description
        TextView descriptionText = new TextView(this);
        descriptionText.setText(description);
        descriptionText.setTextSize(14);
        descriptionText.setTextColor(mutedGreen);
        descriptionText.setPadding(0, 8, 0, 0);

        card.addView(title);
        card.addView(descriptionText);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(0, 0, 0, 16);
        card.setLayoutParams(params);

        return card;
    }
}
