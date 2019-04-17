package com.example.replate.activities.Dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.replate.R;
import com.example.replate.models.PickupRequest;

public class PickupDetail extends AppCompatActivity {

    EditText editTextPickupDate;
    EditText editTextPickupTime;
    EditText editTextPickupInstructions;
    EditText editTextPickupNotes;
    String token;
    PickupRequest pickupRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_detail);

        Intent intent = getIntent();
        pickupRequest = (PickupRequest)intent.getSerializableExtra("result");
        token = intent.getStringExtra("token");

        editTextPickupDate = findViewById(R.id.editText_detail_schedule_pickup_date);
        editTextPickupTime = findViewById(R.id.editText_detail_schedule_pickup_time);
        editTextPickupInstructions = findViewById(R.id.editText_detail_schedule_pickup_instructions);
        editTextPickupNotes = findViewById(R.id.editText_detail_schedule_pickup_additional_notes);

        editTextPickupDate.setText(pickupRequest.getDate());
        editTextPickupTime.setText(pickupRequest.getTime());
        editTextPickupInstructions.setText(pickupRequest.getInstructions());
        editTextPickupNotes.setText(pickupRequest.getNotes());

    }
}
