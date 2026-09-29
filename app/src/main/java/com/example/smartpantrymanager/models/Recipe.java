package com.example.smartpantrymanager.models;

public class Recipe {
    private int recipe_id;
    private String name;
    private String steps;

    public Recipe(int recipe_id, String name, String steps){
        this.recipe_id = recipe_id;
        this.name = name;
        this.steps = steps;
    }

    public int getRecipe_id(){return recipe_id;}

    public String getName(){return name;}

    public String getSteps(){return steps;}


    public void setName(String name){
        this.name = name;
    }

    public void setSteps(String steps){
        this.steps = steps;
    }

}
