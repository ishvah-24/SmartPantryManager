package com.example.smartpantrymanager.models;

public class PantryItem {
    private int pantry_id;
    private String name;
    private String normalized_name;
    private Double quantity;
    private String unit;
    private String expiry_date;

    public PantryItem(String name, String normalized_name, Double quantity, String unit, String expiry_date){
        this.name = name;
        this.normalized_name = normalized_name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiry_date = expiry_date;
    }

    public int getPantry_id(){return pantry_id;}

    public String getName(){return name;}

    public String getNormalizedName(){return normalized_name;}

    public Double getQuantity(){return quantity;}

    public String getUnit(){return unit;}

    public String getExpiryDate(){return expiry_date;}


    public void setName(String name){
        this.name = name;
    }

    public void setNormalized_name(String normalized_name){
        this.normalized_name = normalized_name;
    }

    public void setQuantity(Double quantity){
        this.quantity = quantity;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public void setExpiryDate(String expiry_date){
        this.expiry_date = expiry_date;
    }

    public void setId(int pantry_id) {
        this.pantry_id = pantry_id;
    }
}
