package com.example.replate.activities.Dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.replate.R;
import com.example.replate.models.PickupRequest;
import com.example.replate.models.User;

public class PickupDetail extends AppCompatActivity {

    EditText editTextPickupDate;
    EditText editTextPickupTime;
    EditText editTextPickupInstructions;
    EditText editTextPickupNotes;
    User user;
    PickupRequest pickupRequest;
    Button buttonSubmitChanges;
    Button buttonAcceptPickup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_detail);

        Intent intent = getIntent();
        pickupRequest = (PickupRequest)intent.getSerializableExtra("result");
        user = (User)intent.getSerializableExtra("user");

        editTextPickupDate = findViewById(R.id.editText_detail_schedule_pickup_date);
        editTextPickupTime = findViewById(R.id.editText_detail_schedule_pickup_time);
        editTextPickupInstructions = findViewById(R.id.editText_detail_schedule_pickup_instructions);
        editTextPickupNotes = findViewById(R.id.editText_detail_schedule_pickup_additional_notes);
        buttonSubmitChanges = findViewById(R.id.button_detail_schedule_pickup_submit_changes);
        buttonAcceptPickup = findViewById(R.id.button_detail_schedule_pickup_accept_pickup);

        editTextPickupDate.setText(pickupRequest.getDate());
        editTextPickupTime.setText(pickupRequest.getTime());
        editTextPickupInstructions.setText(pickupRequest.getInstructions());
        editTextPickupNotes.setText(pickupRequest.getNotes());

        if (user.getType().equals("volunteer")) {

        }





    }
}
