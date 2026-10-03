package com.example.smartpantrymanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.R;
import com.example.smartpantrymanager.activities.AddEditIngredientActivity;
import com.example.smartpantrymanager.models.PantryItem;

import java.util.List;
import android.content.Intent;
import com.example.smartpantrymanager.activities.AddEditIngredientActivity;
public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private List<PantryItem> pantryItems;

    public PantryAdapter(List<PantryItem> pantryItems) {
        this.pantryItems = pantryItems;
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry, parent, false);

        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        PantryItem item = pantryItems.get(position);

        holder.nameText.setText(item.getName());
        holder.quantityText.setText(item.getQuantity() + " " + item.getUnit());

        if (item.getExpiryDate() == null || item.getExpiryDate().isEmpty()) {
            holder.expiryText.setText("No expiry date");
        } else {
            holder.expiryText.setText("Expires: " + item.getExpiryDate());
        }

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AddEditIngredientActivity.class);
            intent.putExtra("pantry_id", item.getPantry_id());
            view.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return pantryItems.size();
    }

    public void updateList(List<PantryItem> newItems) {
        pantryItems = newItems;
        notifyDataSetChanged();
    }

    public static class PantryViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        TextView quantityText;
        TextView expiryText;

        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.nameText);
            quantityText = itemView.findViewById(R.id.quantityText);
            expiryText = itemView.findViewById(R.id.expiryText);
        }
    }
}