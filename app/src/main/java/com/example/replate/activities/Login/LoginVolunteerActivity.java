package com.example.replate.activities.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.VolunteerDashBoard;
import com.example.replate.activities.Signup.BusinessSignup;
import com.example.replate.activities.Signup.VolunteerSignup;
import com.example.replate.models.Volunteer;

public class LoginVolunteerActivity extends AppCompatActivity {

    Button loginButton;
    TextView signupTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_volunteer);

        loginButton = findViewById(R.id.button_login_volunteer_login);
        signupTextview = findViewById(R.id.text_view_sign_up_volunteer_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VolunteerDashBoard.class);
                startActivity(intent);
            }
        });

        signupTextview.setOnClickListener(new View.OnClickListener() {//Signup Clicked
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VolunteerSignup.class);
                startActivity(intent);
            }
        });
    }
}
