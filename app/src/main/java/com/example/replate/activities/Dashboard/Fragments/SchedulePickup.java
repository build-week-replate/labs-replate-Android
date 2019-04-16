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

import com.example.replate.R;
import com.example.replate.models.PickupRequest;

public class SchedulePickup extends Fragment {

public interface OnSubmitListener {
    void onSubmitPickup(PickupRequest pickupRequest);

}

    public void submitPickupRequest(View v) {
        // PickupRequest pickupRequest = new PickupRequest();

        //listener.onSubmitPickup("some link");
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

        buttonSchedulePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPickupRequest(v);
            }
        });
    }



    OnSubmitListener listener;

    public SchedulePickup() {
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        String someTitle = getArguments().getString("someTitle", "");
    }
}
