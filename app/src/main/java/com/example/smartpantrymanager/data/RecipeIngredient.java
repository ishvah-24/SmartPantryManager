package com.example.smartpantrymanager.data;

public class RecipeIngredient {
    private int recipe_ingredient_id;
    private int recipe_id;
    private String name;
    private Float quantity;
    private String unit;

    public RecipeIngredient(int recipe_ingredient_id, int recipe_id,
                            String name, Float quantity,
                            String unit){
        this.recipe_ingredient_id = recipe_ingredient_id;
        this.recipe_id = recipe_id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getRecipe_ingredient_id(){return recipe_ingredient_id;}

    public int getRecipe_id(){return recipe_id;}

    public String getName() {return name;}

    public Float getQuantity(){return quantity;}

    public String getUnit(){return unit;}

    public void setName(String name){
        this.name = name;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
