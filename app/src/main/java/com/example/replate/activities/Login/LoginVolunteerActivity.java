package com.example.replate.activities.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.VolunteerDashBoard;
import com.example.replate.activities.Signup.VolunteerSignup;
import com.example.replate.daos.UserLoginDao;
import com.example.replate.models.User;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginVolunteerActivity extends AppCompatActivity {

    Button loginButton;
    TextView signupTextview;
    EditText editTextEmail;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_login);

        loginButton = findViewById(R.id.button_login_volunteer_login);
        signupTextview = findViewById(R.id.text_view_sign_up_volunteer_login);
        editTextEmail = findViewById(R.id.edit_text_email_volunteer_login);
        editTextPassword = findViewById(R.id.edit_text_password_volunteer_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editTextEmail.getText().toString();
                final String password = editTextPassword.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = UserLoginDao.loginToAccount(email, password);
                        Intent intent = new Intent(getApplicationContext(), VolunteerDashBoard.class);
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(result);
                            User user = new User(jsonObject);
                            intent.putExtra("result", user);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
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
