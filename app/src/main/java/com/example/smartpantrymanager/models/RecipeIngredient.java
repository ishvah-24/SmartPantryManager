
package com.example.smartpantrymanager.models;

public class RecipeIngredient {

    private int recipe_ingredient_id;
    private int recipe_id;
    private String name;
    private Float quantity;
    private String unit;

    // Constructor used when creating a new ingredient
    public RecipeIngredient(String name, Float quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Constructor used when loading an ingredient from the database
    public RecipeIngredient(int recipeId, String name, double quantity, String unit) {
        this.recipe_id = recipeId;
        this.name = name;
        this.quantity = (float) quantity;
        this.unit = unit;
    }

    public int getRecipe_ingredient_id() {
        return recipe_ingredient_id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public String getName() {
        return name;
    }

    public Float getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
