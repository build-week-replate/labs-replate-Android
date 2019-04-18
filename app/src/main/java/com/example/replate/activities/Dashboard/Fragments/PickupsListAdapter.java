package com.example.replate.activities.Dashboard.Fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.PickupDetail;
import com.example.replate.models.PickupRequest;
import com.example.replate.models.User;

import java.util.ArrayList;

public class PickupsListAdapter extends RecyclerView.Adapter<PickupsListAdapter.ViewHolder> {
    private ArrayList<PickupRequest> pickupRequests;
    private User user;

    public PickupsListAdapter(ArrayList<PickupRequest> myDataset, User user) {
        this.pickupRequests = myDataset;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_single_element, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final PickupRequest pickupRequest = pickupRequests.get(i);

        if(pickupRequest.getVolunteer_id() == user.getId()) {
            viewHolder.cardView.setCardBackgroundColor(viewHolder.cardView.getResources().getColor(R.color.colorPrimaryLightGreen));
        }else if (pickupRequest.isTaken()) {
            viewHolder.cardView.setCardBackgroundColor(viewHolder.cardView.getResources().getColor(R.color.colorAccentMediumGray));
        }
        viewHolder.textView.setText(pickupRequest.getName());
        viewHolder.textView2.setText(pickupRequest.getDate());
        viewHolder.textView3.setText(pickupRequest.getTime());
        viewHolder.textView5.setText(pickupRequest.getOfficeName());
        viewHolder.textView6.setText(pickupRequest.getOfficeAddress());
        if (pickupRequest.getInstructions().length() > 50) {
            viewHolder.textView4.setText(pickupRequest.getInstructions().substring(0, 50) + "...");
        }
        else {
            viewHolder.textView4.setText(pickupRequest.getInstructions());
        }
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PickupDetail.class);
                intent.putExtra("user", user);
                intent.putExtra("result", pickupRequest);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pickupRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recycler_single_element_text1);
            textView2 = itemView.findViewById(R.id.recycler_single_element_text2);
            textView3 = itemView.findViewById(R.id.recycler_single_element_text3);
            textView4 = itemView.findViewById(R.id.recycler_single_element_text4);
            textView5 = itemView.findViewById(R.id.recycler_single_element_text5);
            textView6 = itemView.findViewById(R.id.recycler_single_element_text6);
            cardView = itemView.findViewById(R.id.recycler_cardView);
        }
    }
}

