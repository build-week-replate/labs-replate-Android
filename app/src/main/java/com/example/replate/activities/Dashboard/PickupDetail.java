package com.example.replate.activities.Dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.replate.R;
import com.example.replate.daos.PickupRequestsDao;
import com.example.replate.models.PickupRequest;
import com.example.replate.models.User;

public class PickupDetail extends AppCompatActivity {


    public static final String RESULT = "result";
    public static final String USER = "user";
    public static final String VOLUNTEER = "volunteer";
    EditText editTextPickupName;
    EditText editTextPickupDate;
    EditText editTextPickupTime;
    EditText editTextPickupOfficeName;
    EditText editTextPickupOfficeAddress;
    EditText editTextPickupInstructions;
    EditText editTextPickupNotes;
    User user;
    PickupRequest pickupRequest;
    Button buttonSubmitChanges;
    Button buttonAcceptPickup;
    Button buttonMarkComplete;
    Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_detail);

        Intent intent = getIntent();
        pickupRequest = (PickupRequest)intent.getSerializableExtra(RESULT);
        user = (User)intent.getSerializableExtra(USER);

        editTextPickupName = findViewById(R.id.editText_detail_schedule_pickup_name);
        editTextPickupDate = findViewById(R.id.editText_detail_schedule_pickup_date);
        editTextPickupTime = findViewById(R.id.editText_detail_schedule_pickup_time);
        editTextPickupOfficeName = findViewById(R.id.editText_detail_schedule_pickup_office_name);
        editTextPickupOfficeAddress = findViewById(R.id.editText_detail_schedule_pickup_office_address);
        editTextPickupInstructions = findViewById(R.id.editText_detail_schedule_pickup_instructions);
        editTextPickupNotes = findViewById(R.id.editText_detail_schedule_pickup_additional_notes);
        buttonSubmitChanges = findViewById(R.id.button_detail_schedule_pickup_submit_changes);
        buttonAcceptPickup = findViewById(R.id.button_detail_schedule_pickup_accept_pickup);
        buttonMarkComplete = findViewById(R.id.button_detail_schedule_pickup_complete_pickup);
        buttonDelete = findViewById(R.id.button_detail_schedule_pickup_delete_pickup);

        editTextPickupName.setText(pickupRequest.getName());
        editTextPickupDate.setText(pickupRequest.getDate());
        editTextPickupTime.setText(pickupRequest.getTime());
        editTextPickupOfficeName.setText(pickupRequest.getOfficeName());
        editTextPickupOfficeAddress.setText(pickupRequest.getOfficeAddress());
        editTextPickupInstructions.setText(pickupRequest.getInstructions());
        editTextPickupNotes.setText(pickupRequest.getNotes());

        if (user.getType().equals(VOLUNTEER)) {
            editTextPickupDate.setEnabled(false);
            editTextPickupTime.setEnabled(false);
            editTextPickupInstructions.setEnabled(false);
            editTextPickupNotes.setEnabled(false);
            if (user.getId() == pickupRequest.getVolunteer_id()) { //volunteer and accepted pickup
                buttonMarkComplete.setVisibility(View.VISIBLE);
                buttonSubmitChanges.setVisibility(View.GONE);
                buttonDelete.setVisibility(View.GONE);
            } else {
                buttonAcceptPickup.setVisibility(View.VISIBLE); //volunteer not accepted pickup
                buttonSubmitChanges.setVisibility(View.GONE);
                buttonDelete.setVisibility(View.GONE);
            }
        } //else it's prepped for a business' view

        buttonSubmitChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PickupRequest pickupRequestEdited = new PickupRequest(
                        user.getName(),
                        editTextPickupTime.getText().toString(),
                        editTextPickupDate.getText().toString(),
                        editTextPickupInstructions.getText().toString(),
                        editTextPickupNotes.getText().toString(),
                        pickupRequest.getId(),
                        pickupRequest.getLocationId()
                );
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PickupRequestsDao.editPickupRequest(pickupRequestEdited, user.getToken());
                    }
                }).start();
                finish();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PickupRequestsDao.deletePickup(pickupRequest, user.getToken());
                    }
                }).start();
                finish();
            }
        });

        buttonAcceptPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PickupRequestsDao.assignPickup(pickupRequest, user.getToken());
                    }
                }).start();
                finish();
            }

        });

        buttonMarkComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
