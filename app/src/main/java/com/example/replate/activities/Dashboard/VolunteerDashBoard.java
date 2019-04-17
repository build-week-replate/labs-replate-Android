package com.example.replate.activities.Dashboard;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.Fragments.PickupDisplayFragment;
import com.example.replate.models.User;

public class VolunteerDashBoard extends AppCompatActivity {

    PickupDisplayFragment fragment;
    Button myPickupsButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_dashboard);
        myPickupsButton = findViewById(R.id.button_volunteer_dashboard_my_pickups);

        Intent intent = getIntent();
        final User user = (User) intent.getSerializableExtra("result");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment = newInstance(user.getName(), user.getToken());
        ft.replace(R.id.frame_layout_volunteer_pickups, fragment);
        ft.commitNow();
        fragment.getAllPickups();

        myPickupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                fragment = newInstance(user.getName(), user.getToken());
                ft.replace(R.id.frame_layout_volunteer_pickups, fragment);
                ft.commitNow();
                fragment.getVolunteerPickups(user.getId());
            }
        });
    }

    public static PickupDisplayFragment newInstance(String username, String token) {
        PickupDisplayFragment fragment = new PickupDisplayFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
        args.putString("token", token);
        fragment.setArguments(args);
        return fragment;
    }
}
