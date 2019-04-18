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
    private static final int DESCRIPTION_MAX_LENGTH = 45;
    private static final String USER = "user";
    private static final String RESULT = "result";
    private static final String ELIPSES = "..";

    private ArrayList<PickupRequest> pickupRequests;
    private User user;

    PickupsListAdapter(ArrayList<PickupRequest> myDataset, User user) {
        this.pickupRequests = myDataset;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_single_element, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final PickupRequest pickupRequest = pickupRequests.get(i);

        if(pickupRequest.getVolunteer_id() == user.getId()) {
            viewHolder.cardViewParent.setCardBackgroundColor(viewHolder.cardViewParent.getResources().getColor(R.color.colorPrimaryLightGreen));
        }else if (pickupRequest.isTaken()) {
            viewHolder.cardViewParent.setCardBackgroundColor(viewHolder.cardViewParent.getResources().getColor(R.color.colorAccentMediumGray));
        }
        viewHolder.textViewName.setText(pickupRequest.getName());
        viewHolder.textViewDate.setText(pickupRequest.getDate());
        viewHolder.textViewTime.setText(pickupRequest.getTime());
        viewHolder.textViewLocationName.setText(pickupRequest.getOfficeName());
        viewHolder.textViewLocationAddress.setText(pickupRequest.getOfficeAddress());
        if (pickupRequest.getInstructions().length() > DESCRIPTION_MAX_LENGTH) {
            String temp = (pickupRequest.getInstructions().substring(0, DESCRIPTION_MAX_LENGTH) + ELIPSES);
            viewHolder.textViewInstructions.setText(temp);
        }
        else {
            viewHolder.textViewInstructions.setText(pickupRequest.getInstructions());
        }
        viewHolder.cardViewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PickupDetail.class);
                intent.putExtra(USER, user);
                intent.putExtra(RESULT, pickupRequest);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pickupRequests.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDate;
        TextView textViewTime;
        TextView textViewInstructions;
        TextView textViewLocationName;
        TextView textViewLocationAddress;
        CardView cardViewParent;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView_recycler_single_element_name);
            textViewDate = itemView.findViewById(R.id.textView_recycler_single_element_date);
            textViewTime = itemView.findViewById(R.id.textView_recycler_single_element_time);
            textViewInstructions = itemView.findViewById(R.id.textView_recycler_single_element_instructions);
            textViewLocationName = itemView.findViewById(R.id.textView_recycler_single_element_location_name);
            textViewLocationAddress = itemView.findViewById(R.id.textView_recycler_single_element_location_address);
            cardViewParent = itemView.findViewById(R.id.recycler_cardView);
        }
    }
}

