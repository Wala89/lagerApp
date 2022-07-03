package com.example.lagerapptest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagerapptest.database.allitems.Item;

import java.util.ArrayList;

public class ItemRecViewAdapter extends RecyclerView.Adapter<ItemRecViewAdapter.ViewHolder>{

    private ArrayList<Item> arrayListItems = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(arrayListItems.get(position).getName());
        holder.txtCapacity.setText(String.valueOf(arrayListItems.get(position).getCapacity()));
        holder.txtDuration.setText(arrayListItems.get(position).getDurabilityDate());

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.txtDuration.setText(arrayListItems.get(position).getCode());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListItems.size();
    }

    // *** Getter and Setter **********************************************************************


    public ArrayList<Item> getArrayListItems() {
        return arrayListItems;
    }

    public void setArrayListItems(ArrayList<Item> arrayListItems) {
        this.arrayListItems = arrayListItems;
        notifyDataSetChanged();
    }

    public void addItemToList(Item item) {
        this.arrayListItems.add(item);
        notifyDataSetChanged();
    }

    // *** ViewHolder Class ***********************************************************************
    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView txtName, txtCapacity, txtDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            txtName = itemView.findViewById(R.id.txtItemLayoutName);
            txtCapacity = itemView.findViewById(R.id.txtItemLayoutCapacity);
            txtDuration = itemView.findViewById(R.id.txtItemLayoutDuration);
        }
    }
}
