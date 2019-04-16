package com.example.replate.activities.Dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.Fragments.SchedulePickup;
import com.example.replate.models.PickupRequest;

public class BusinessDashboard extends AppCompatActivity implements SchedulePickup.OnSubmitListener {
    Button schedulePickup;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_dashboard);

        schedulePickup = findViewById(R.id.button_business_dashboard_schedule_pickup);

        schedulePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                fragment = new SchedulePickup();
                ft.replace(R.id.frame_layout_business_dashboard_center, fragment);
                ft.commit();
                schedulePickup.setVisibility(View.INVISIBLE);
            }
        });

    }


    @Override
    public void onSubmitPickup(PickupRequest pickupRequest) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(fragment);
        ft.commit();
        schedulePickup.setVisibility(View.VISIBLE);
    }
}
