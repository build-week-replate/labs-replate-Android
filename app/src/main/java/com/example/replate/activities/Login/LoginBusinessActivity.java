package com.example.replate.activities.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.BusinessDashboard;
import com.example.replate.activities.Signup.BusinessSignup;
import com.example.replate.daos.UserLoginDao;
import com.example.replate.models.User;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginBusinessActivity extends AppCompatActivity {

    Button loginButton;
    TextView textViewSignup;
    EditText editTextEmail;
    EditText editTextPassword;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);
        context = this;

        textViewSignup = findViewById(R.id.text_view_sign_up_business_login);
        loginButton = findViewById(R.id.button_login_business_login);
        editTextEmail = findViewById(R.id.edit_text_email_business_login);
        editTextPassword = findViewById(R.id.edit_text_password_business_login);

        loginButton.setOnClickListener(new View.OnClickListener() { //Login Clicked
            @Override
            public void onClick(View v) {

                final String email = editTextEmail.getText().toString();
                final String password = editTextPassword.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = UserLoginDao.loginToAccount(email, password);
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(result);
                            User user = new User(jsonObject);
                            if (user.getType().equals("volunteer")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "You are in the wrong login screen", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Intent intent = new Intent(getApplicationContext(), BusinessDashboard.class);
                                intent.putExtra("result", user);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });

        textViewSignup.setOnClickListener(new View.OnClickListener() {//Signup Clicked
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusinessSignup.class);
                startActivity(intent);
            }
        });
    }
}
