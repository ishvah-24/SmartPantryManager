package com.example.smartpantrymanager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.activities.PantryActivity;
import com.example.smartpantrymanager.activities.SuggestedRecipesActivity;
import com.example.smartpantrymanager.activities.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    // Fresh & Editorial colour palette
    private final int backgroundColor = Color.rgb(247, 244, 235);
    private final int darkGreen = Color.rgb(32, 61, 48);
    private final int mutedGreen = Color.rgb(105, 118, 107);
    private final int sageGreen = Color.rgb(220, 232, 213);
    private final int citrusAmber = Color.rgb(233, 162, 59);
    private final int cardWhite = Color.rgb(255, 254, 250);
    private final int borderColor = Color.rgb(227, 230, 218);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Main screen content
        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(backgroundColor);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(dp(24), dp(28), dp(24), dp(20));
        layout.setBackgroundColor(backgroundColor);

        scrollView.addView(layout);

        // Small brand label
        TextView appLabel = new TextView(this);
        appLabel.setText("THE EVERYDAY PANTRY");
        appLabel.setTextSize(12);
        appLabel.setLetterSpacing(0.16f);
        appLabel.setTextColor(mutedGreen);
        appLabel.setTypeface(null, Typeface.BOLD);

        layout.addView(appLabel);

        // Editorial heading
        TextView title = new TextView(this);
        title.setText("Good food.\nLess waste.");
        title.setTextSize(36);
        title.setTextColor(darkGreen);
        title.setTypeface(Typeface.create("serif", Typeface.BOLD));
        title.setLineSpacing(dp(2), 1.0f);
        title.setPadding(0, dp(12), 0, dp(10));

        layout.addView(title);

        // Introductory text
        TextView subtitle = new TextView(this);
        subtitle.setText(
                "Make the most of what you have, "
                        + "one ingredient at a time."
        );
        subtitle.setTextSize(16);
        subtitle.setTextColor(mutedGreen);
        subtitle.setLineSpacing(dp(4), 1.0f);
        subtitle.setPadding(0, 0, 0, dp(24));

        layout.addView(subtitle);

        // Featured welcome card
        LinearLayout heroCard = new LinearLayout(this);
        heroCard.setOrientation(LinearLayout.VERTICAL);
        heroCard.setPadding(dp(22), dp(22), dp(22), dp(22));
        heroCard.setBackground(createBackground(
                darkGreen, darkGreen, 24
        ));

        TextView heroEyebrow = new TextView(this);
        heroEyebrow.setText("YOUR KITCHEN, SIMPLIFIED");
        heroEyebrow.setTextSize(11);
        heroEyebrow.setLetterSpacing(0.12f);
        heroEyebrow.setTextColor(sageGreen);
        heroEyebrow.setTypeface(null, Typeface.BOLD);

        TextView heroTitle = new TextView(this);
        heroTitle.setText("A little more\nlove for your food.");
        heroTitle.setTextSize(25);
        heroTitle.setTextColor(cardWhite);
        heroTitle.setTypeface(Typeface.create("serif", Typeface.BOLD));
        heroTitle.setPadding(0, dp(12), 0, dp(8));

        TextView heroDescription = new TextView(this);
        heroDescription.setText(
                "Keep track of your ingredients and "
                        + "find recipes you can actually make."
        );
        heroDescription.setTextSize(14);
        heroDescription.setTextColor(sageGreen);
        heroDescription.setLineSpacing(dp(3), 1.0f);

        heroCard.addView(heroEyebrow);
        heroCard.addView(heroTitle);
        heroCard.addView(heroDescription);

        LinearLayout.LayoutParams heroParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        heroParams.bottomMargin = dp(28);

        layout.addView(heroCard, heroParams);

        // Section heading
        TextView sectionTitle = new TextView(this);
        sectionTitle.setText("YOUR KITCHEN");
        sectionTitle.setTextSize(12);
        sectionTitle.setLetterSpacing(0.14f);
        sectionTitle.setTextColor(mutedGreen);
        sectionTitle.setTypeface(null, Typeface.BOLD);
        sectionTitle.setPadding(0, 0, 0, dp(14));

        layout.addView(sectionTitle);

        // My Pantry card
        LinearLayout pantryCard = createCard(
                "🥬",
                "My Pantry",
                "Your ingredients, all in one place.",
                sageGreen
        );

        pantryCard.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    PantryActivity.class
            );
            startActivity(intent);
        });

        layout.addView(pantryCard);

        // Find Recipes card
        LinearLayout recipesCard = createCard(
                "🍋",
                "Find Recipes",
                "Discover what you can make today.",
                cardWhite
        );

        recipesCard.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SuggestedRecipesActivity.class
            );
            startActivity(intent);
        });

        layout.addView(recipesCard);

        // Settings button
        Button settingsButton = new Button(this);
        settingsButton.setText("Settings  →");
        settingsButton.setTextSize(14);
        settingsButton.setTextColor(darkGreen);
        settingsButton.setAllCaps(false);
        settingsButton.setTypeface(null, Typeface.BOLD);
        settingsButton.setGravity(Gravity.CENTER);
        settingsButton.setPadding(
                dp(12), dp(8), dp(12), dp(8)
        );
        settingsButton.setBackground(createBackground(
                backgroundColor, borderColor, 16
        ));

        LinearLayout.LayoutParams settingsParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(52)
                );

        settingsParams.topMargin = dp(12);

        layout.addView(settingsButton, settingsParams);

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SettingsActivity.class
            );
            startActivity(intent);
        });

        setContentView(scrollView);
    }

    // Creates a reusable pantry or recipe card
    private LinearLayout createCard(
            String icon,
            String heading,
            String description,
            int cardColor) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(dp(18), dp(20), dp(16), dp(20));

        card.setBackground(createBackground(
                cardColor, borderColor, 22
        ));

        // Icon container
        TextView iconView = new TextView(this);
        iconView.setText(icon);
        iconView.setTextSize(25);
        iconView.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams iconParams =
                new LinearLayout.LayoutParams(
                        dp(52), dp(52)
                );
        iconParams.rightMargin = dp(14);

        card.addView(iconView, iconParams);

        // Text container
        LinearLayout textContainer = new LinearLayout(this);
        textContainer.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(this);
        title.setText(heading);
        title.setTextSize(19);
        title.setTextColor(darkGreen);
        title.setTypeface(null, Typeface.BOLD);

        TextView descriptionText = new TextView(this);
        descriptionText.setText(description);
        descriptionText.setTextSize(13);
        descriptionText.setTextColor(mutedGreen);
        descriptionText.setPadding(0, dp(5), 0, 0);

        textContainer.addView(title);
        textContainer.addView(descriptionText);

        LinearLayout.LayoutParams textParams =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                );

        card.addView(textContainer, textParams);

        // Right arrow accent
        TextView arrow = new TextView(this);
        arrow.setText("›");
        arrow.setTextSize(30);
        arrow.setTextColor(darkGreen);
        arrow.setGravity(Gravity.CENTER);

        card.addView(arrow, new LinearLayout.LayoutParams(
                dp(24),
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        cardParams.bottomMargin = dp(12);
        card.setLayoutParams(cardParams);

        return card;
    }

    // Creates a rounded background with an optional border
    private GradientDrawable createBackground(
            int fillColor,
            int strokeColor,
            int cornerRadiusDp) {

        GradientDrawable shape = new GradientDrawable();
        shape.setColor(fillColor);
        shape.setCornerRadius(dp(cornerRadiusDp));
        shape.setStroke(dp(1), strokeColor);

        return shape;
    }

    // Converts density-independent pixels to screen pixels
    private int dp(float value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density + 0.5f);
    }
}
