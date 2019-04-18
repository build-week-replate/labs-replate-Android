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
import android.widget.TextView;

import com.example.replate.R;
import com.example.replate.models.OfficeLocation;
import com.example.replate.models.PickupRequest;
import com.example.replate.models.User;


public class AddLocationFragment extends Fragment {

    public static final String MUST_IMPLEMENT_ADD_LOCATION_FRAGMENT_ON_ADD_LOCATION_LISTENER =
            " must implement AddLocationFragment.OnAddLocationListener";
    public static final String USER = "user";

    OnAddLocationListener listener;

    EditText editTextLocationName;
    EditText editTextLocationAddress;
    EditText editTextLocationEmail;
    Button buttonAddLocation;
    User user;


    public AddLocationFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User)getArguments().getSerializable(USER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        editTextLocationName = view.findViewById(R.id.editText_add_location_office_name);
        editTextLocationAddress = view.findViewById(R.id.editText_add_location_office_address);
        editTextLocationEmail = view.findViewById(R.id.editText_add_location_office_email);
        buttonAddLocation = view.findViewById(R.id.button_add_location);


        buttonAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OfficeLocation officeLocation = new OfficeLocation(
                        editTextLocationName.getText().toString(),
                        editTextLocationAddress.getText().toString(),
                        editTextLocationEmail.getText().toString()
                );
                addLocationRequest(officeLocation, user.getToken());
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            listener = (OnAddLocationListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + MUST_IMPLEMENT_ADD_LOCATION_FRAGMENT_ON_ADD_LOCATION_LISTENER);
        }
    }
    public interface OnAddLocationListener {
        void onAddLocationListener(OfficeLocation officeLocation, String token);
    }

    public void addLocationRequest(OfficeLocation officeLocation, String token) {
        listener.onAddLocationListener(officeLocation, token);
    }

}
