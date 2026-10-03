package com.example.smartpantrymanager.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.models.PantryItem;

import java.util.ArrayList;
import java.util.List;

public class PantryDAO {

    private final DatabaseHelper dbHelper;

    public PantryDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // CREATE - Add a pantry item
    public long insert(PantryItem item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("normalized_name", item.getNormalizedName());
        values.put("quantity", item.getQuantity());
        values.put("unit", item.getUnit());
        values.put("expiry_date", item.getExpiryDate());

        return db.insert("pantry_items", null, values);
    }

    // READ - Get all pantry items
    public List<PantryItem> getAll() {
        List<PantryItem> items = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "pantry_items",
                null,
                null,
                null,
                null,
                null,
                "name ASC"
        );

        while (cursor.moveToNext()) {
            items.add(cursorToPantryItem(cursor));
        }

        cursor.close();

        return items;
    }

    // READ - Get one pantry item by ID
    public PantryItem getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "pantry_items",
                null,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        PantryItem item = null;

        if (cursor.moveToFirst()) {
            item = cursorToPantryItem(cursor);
        }

        cursor.close();

        return item;
    }

    // UPDATE - Update an existing pantry item
    public int update(PantryItem item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("normalized_name", item.getNormalizedName());
        values.put("quantity", item.getQuantity());
        values.put("unit", item.getUnit());
        values.put("expiry_date", item.getExpiryDate());

        return db.update(
                "pantry_items",
                values,
                "id = ?",
                new String[]{String.valueOf(item.getPantry_id())}
        );
    }

    // DELETE - Delete a pantry item
    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.delete(
                "pantry_items",
                "id = ?",
                new String[]{String.valueOf(id)}
        );
    }

    // Convert a database row into a PantryItem object
    private PantryItem cursorToPantryItem(Cursor cursor) {

        PantryItem item = new PantryItem();

        item.setPantry_id(
                cursor.getInt(
                        cursor.getColumnIndexOrThrow("id")
                )
        );

        item.setName(
                cursor.getString(
                        cursor.getColumnIndexOrThrow("name")
                )
        );

        item.setNormalizedName(
                cursor.getString(
                        cursor.getColumnIndexOrThrow("normalized_name")
                )
        );

        item.setQuantity(
                cursor.getDouble(
                        cursor.getColumnIndexOrThrow("quantity")
                )
        );

        item.setUnit(
                cursor.getString(
                        cursor.getColumnIndexOrThrow("unit")
                )
        );

        int expiryIndex =
                cursor.getColumnIndexOrThrow("expiry_date");

        if (cursor.isNull(expiryIndex)) {
            item.setExpiryDate(null);
        } else {
            item.setExpiryDate(
                    cursor.getString(expiryIndex)
            );
        }

        return item;
    }
}