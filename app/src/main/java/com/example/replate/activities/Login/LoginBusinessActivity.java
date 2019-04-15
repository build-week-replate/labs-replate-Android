package com.example.replate.activities.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.BusinessDashboard;
import com.example.replate.activities.Dashboard.VolunteerDashBoard;
import com.example.replate.activities.Signup.BusinessSignup;

public class LoginBusinessActivity extends AppCompatActivity {

    Button loginButton;
    TextView signupTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_business);

        signupTextview = findViewById(R.id.text_view_sign_up_business_login);
        loginButton = findViewById(R.id.button_login_business_login);

        loginButton.setOnClickListener(new View.OnClickListener() { //Login Clicked
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusinessDashboard.class);
                startActivity(intent);
            }
        });

        signupTextview.setOnClickListener(new View.OnClickListener() {//Signup Clicked
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusinessSignup.class);
                startActivity(intent);
            }
        });
    }
}
