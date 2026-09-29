package com.example.smartpantrymanager.database;

public class PantryItem {
    private int pantry_id;
    private String name;
    private String normalized_name;
    private Float quantity;
    private String unit;
    private String expiry_date;

    public PantryItem(int pantry_id, String name, String normalized_name, float quantity, String unit, String expiry_date){
        this.pantry_id = pantry_id;
        this.name = name;
        this.normalized_name = normalized_name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiry_date = expiry_date;
    }

    public int getPantry_id(){return pantry_id;}

    public String getName(){return name;}

    public String getNormalized_name(){return normalized_name;}

    public Float getQuantity(){return quantity;}

    public String getUnit(){return unit;}

    public String getExpiry_date(){return expiry_date;}


    public void setName(String name){
        this.name = name;
    }

    public void setNormalized_name(String normalized_name){
        this.normalized_name = normalized_name;
    }

    public void setQuantity(Float quantity){
        this.quantity = quantity;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public void setExpiryDate(String expiry_date){
        this.expiry_date = expiry_date;
    }
}
