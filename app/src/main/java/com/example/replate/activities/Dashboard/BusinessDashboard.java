package com.example.replate.activities.Dashboard;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.Fragments.AddLocationFragment;
import com.example.replate.activities.Dashboard.Fragments.PickupsDisplayFragment;
import com.example.replate.activities.Dashboard.Fragments.SchedulePickupFragment;
import com.example.replate.daos.OfficeLocationsDao;
import com.example.replate.daos.PickupRequestsDao;
import com.example.replate.daos.UserLoginDao;
import com.example.replate.models.OfficeLocation;
import com.example.replate.models.PickupRequest;
import com.example.replate.models.User;

public class BusinessDashboard extends AppCompatActivity implements SchedulePickupFragment.OnSubmitListener, AddLocationFragment.OnAddLocationListener {


    Button buttonSchedulePickup;
    Button buttonMyPickups;
    Button buttonAddLocation;
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet;
    Fragment fragment;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_dashboard);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("result");

        buttonSchedulePickup = findViewById(R.id.button_business_dashboard_schedule_pickup);
        buttonMyPickups = findViewById(R.id.button_business_dashboard_my_scheduled_pickups);
        buttonAddLocation = findViewById(R.id.button_business_dashboard_add_location);
        constraintLayout = findViewById(R.id.constraint_layout_business_dashboard_parent);
        constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        buttonAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                fragment = newInstance3(user.getName(), user);
                ft.replace(R.id.frame_layout_business_dashboard_center, fragment);
                ft.commit();
            }
        });


        buttonSchedulePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                fragment = newInstance(user.getName(), user);
                ft.replace(R.id.frame_layout_business_dashboard_center, fragment);
                ft.commit();
            }
        });

        buttonMyPickups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                PickupsDisplayFragment pickupsDisplayFragment = newInstance2(user.getName(), user);
                ft.replace(R.id.frame_layout_business_dashboard_center, pickupsDisplayFragment);
                ft.commitNow();
                pickupsDisplayFragment.getCompanyPickups(user.getId());
            }
        });

    }


    @Override
    public void onSubmitPickup(final PickupRequest pickupRequest, final String token) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(fragment);
        ft.commit();
        new Thread(new Runnable() {
            @Override
            public void run() {
                PickupRequestsDao.postPickupRequest(pickupRequest, token);
            }
        }).start();
    }

    @Override
    public void onAddLocationListener(final OfficeLocation officeLocation, final String token) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(fragment);
        ft.commit();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OfficeLocationsDao.createNewLocation(officeLocation, token);
            }
        }).start();

    }


    public static SchedulePickupFragment newInstance(String username, User user) {
        SchedulePickupFragment fragment = new SchedulePickupFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    public static PickupsDisplayFragment newInstance2(String username, User user) {
        PickupsDisplayFragment fragment = new PickupsDisplayFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    public static AddLocationFragment newInstance3(String username, User user) {
        AddLocationFragment fragment = new AddLocationFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}

