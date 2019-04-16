package com.example.replate.activities.Dashboard.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.replate.R;
import com.example.replate.models.PickupRequest;

public class SchedulePickup extends Fragment {

    OnSubmitListener listener;

    EditText editTextPickupDate;
    EditText editTextPickupTime;
    EditText editTextPickupInstructions;
    EditText editTextPickupNotes;
    String username;
    String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = getArguments().getString("username", null);
        token = getArguments().getString("token", null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_pickup, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonSchedulePickup = view.findViewById(R.id.button_schedule_pickup_submit_order);
        editTextPickupDate = view.findViewById(R.id.editText_schedule_pickup_date);
        editTextPickupTime = view.findViewById(R.id.editText_schedule_pickup_time);
        editTextPickupInstructions = view.findViewById(R.id.editText_schedule_pickup_instructions);
        editTextPickupNotes = view.findViewById(R.id.editText_schedule_pickup_additional_notes);


        buttonSchedulePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               PickupRequest pickupRequest = new PickupRequest(
                       username,
                       editTextPickupTime.getText().toString(),
                       editTextPickupDate.getText().toString(),
                       editTextPickupInstructions.getText().toString(),
                       editTextPickupNotes.getText().toString()
               );

                submitPickupRequest(v, pickupRequest, token);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            listener = (OnSubmitListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement SchedulePickup.OnSubmitListener");
        }
    }
    public interface OnSubmitListener {
        void onSubmitPickup(PickupRequest pickupRequest, String token);
    }

    public void submitPickupRequest(View v, PickupRequest pickupRequest, String token) {
        listener.onSubmitPickup(pickupRequest, token);
    }



}
