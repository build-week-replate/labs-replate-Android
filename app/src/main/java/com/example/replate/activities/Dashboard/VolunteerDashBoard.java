package com.example.replate.activities.Dashboard;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.replate.R;
import com.example.replate.models.User;

public class VolunteerDashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_dashboard);

        Intent intent = getIntent();

        User user = (User)intent.getSerializableExtra("result");

    }
}
