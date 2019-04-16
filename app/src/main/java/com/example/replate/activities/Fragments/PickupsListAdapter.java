package com.example.replate.activities.Fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.replate.R;
import com.example.replate.models.PickupRequest;

import java.util.ArrayList;

public class PickupsListAdapter extends RecyclerView.Adapter<PickupsListAdapter.ViewHolder>  {
    private ArrayList<PickupRequest> pickupRequests;

    public PickupsListAdapter(ArrayList<PickupRequest> myDataset) {
        pickupRequests = myDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_single_element, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return pickupRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recycler_single_element_text1);
            cardView = itemView.findViewById(R.id.recycler_cardView);
        }
    }
}
