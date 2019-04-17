package com.example.replate.activities.Dashboard;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.Fragments.PickupsDisplayFragment;
import com.example.replate.models.User;

public class VolunteerDashBoard extends AppCompatActivity {

    PickupsDisplayFragment fragment;
    Button buttonMyPickups;
    Button buttonAllPickups;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_dashboard);
        buttonMyPickups = findViewById(R.id.button_volunteer_dashboard_my_pickups);
        buttonAllPickups = findViewById(R.id.button_volunteer_dashboard_all_pickups);


        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("result");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment = newInstance(user.getName(), user);
        ft.replace(R.id.frame_layout_volunteer_pickups, fragment);
        ft.commitNow();
        fragment.getAllPickups();

        buttonMyPickups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                fragment = newInstance(user.getName(), user);
                ft.replace(R.id.frame_layout_volunteer_pickups, fragment);
                ft.commitNow();
                fragment.getVolunteerPickups(user.getId());
                buttonAllPickups.setVisibility(View.VISIBLE);
                buttonMyPickups.setVisibility(View.GONE);
            }
        });

        buttonAllPickups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                fragment = newInstance(user.getName(), user);
                ft.replace(R.id.frame_layout_volunteer_pickups, fragment);
                ft.commitNow();
                fragment.getAllPickups();
                buttonAllPickups.setVisibility(View.GONE);
                buttonMyPickups.setVisibility(View.VISIBLE);
            }
        });
    }

    public static PickupsDisplayFragment newInstance(String username, User user) {
        PickupsDisplayFragment fragment = new PickupsDisplayFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }


}
