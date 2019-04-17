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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.replate.R;
import com.example.replate.daos.OfficeLocationsDao;
import com.example.replate.models.OfficeLocation;
import com.example.replate.models.PickupRequest;
import com.example.replate.models.User;
import java.util.ArrayList;

public class SchedulePickupFragment extends Fragment {

    OnSubmitListener listener;

    EditText editTextPickupDate;
    EditText editTextPickupTime;
    EditText editTextPickupInstructions;
    EditText editTextPickupNotes;
    Spinner spinnerLocation;
    String username;
    User user;
    ArrayList<OfficeLocation> locations;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = getArguments().getString("username", null);
        user = (User)getArguments().getSerializable("user");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_pickup, container, false);
    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonSchedulePickup = view.findViewById(R.id.button_detail_schedule_pickup_submit_order);
        editTextPickupDate = view.findViewById(R.id.editText_schedule_pickup_date);
        editTextPickupTime = view.findViewById(R.id.editText_schedule_pickup_time);
        editTextPickupInstructions = view.findViewById(R.id.editText_schedule_pickup_instructions);
        editTextPickupNotes = view.findViewById(R.id.editText_schedule_pickup_additional_notes);
        spinnerLocation = view.findViewById(R.id.spinner_schedule_pickup_location);
        locations = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                locations = OfficeLocationsDao.getBusinessLocationsList(user);
                final ArrayList<String> locationNames = new ArrayList<>(locations.size());
                for (OfficeLocation officeLocation: locations) {
                    locationNames.add(officeLocation.getOfficeName() + " - " + officeLocation.getOfficeAddress());
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(view.getContext(),
                                R.layout.spinner_item, locationNames);
                        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
                        spinnerLocation.setAdapter(dataAdapter);
                    }
                });
            }
        }).start();

        buttonSchedulePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               PickupRequest pickupRequest = new PickupRequest(
                       username,
                       editTextPickupTime.getText().toString(),
                       editTextPickupDate.getText().toString(),
                       editTextPickupInstructions.getText().toString(),
                       editTextPickupNotes.getText().toString(),
                       spinnerLocation.getSelectedItemPosition()+1 //+1 - the backend doesn't have an element 0
               );

                submitPickupRequest(v, pickupRequest, user.getToken());
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
