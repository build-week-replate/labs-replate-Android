package com.example.replate.activities.Login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.replate.R;

public class LoginActivity extends AppCompatActivity {

    Button buttonBusiness;
    Button buttonVolunteer;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonBusiness = findViewById(R.id.button_login_business);
        buttonVolunteer = findViewById(R.id.button_login_volunteer);
        context = this;

        buttonVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginVolunteerActivity.class);
                startActivity(intent);
            }
        });

        buttonBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginBusinessActivity.class);
                startActivity(intent);

            }
        });

    }
}
