package com.example.replate.activities.Dashboard;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.Fragments.SchedulePickupFragment;
import com.example.replate.daos.PickupRequestsDao;
import com.example.replate.models.PickupRequest;
import com.example.replate.models.User;

public class BusinessDashboard extends AppCompatActivity implements SchedulePickupFragment.OnSubmitListener {


    Button schedulePickup;
    Fragment fragment;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_dashboard);

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("result");

        schedulePickup = findViewById(R.id.button_business_dashboard_schedule_pickup);

        schedulePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                fragment = newInstance(user.getName(), user.getToken());
                ft.replace(R.id.frame_layout_business_dashboard_center, fragment);
                ft.commit();
                schedulePickup.setVisibility(View.INVISIBLE);
            }
        });

    }


    @Override
    public void onSubmitPickup(final PickupRequest pickupRequest, final String token) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(fragment);
        ft.commit();
        schedulePickup.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                PickupRequestsDao.postPickupRequest(pickupRequest, token);
            }
        }).start();
    }


    public static SchedulePickupFragment newInstance(String username, String token) {
        SchedulePickupFragment fragment = new SchedulePickupFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
        args.putString("token", token);
        fragment.setArguments(args);
        return fragment;
    }
}
