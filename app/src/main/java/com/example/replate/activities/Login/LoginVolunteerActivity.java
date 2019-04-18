package com.example.replate.activities.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.BusinessDashboard;
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
    Context context;
    View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_login);
        context = this;

        loginButton = findViewById(R.id.button_login_volunteer_login);
        signupTextview = findViewById(R.id.text_view_sign_up_volunteer_login);
        editTextEmail = findViewById(R.id.edit_text_email_volunteer_login);
        editTextPassword = findViewById(R.id.edit_text_password_volunteer_login);
        rootView = findViewById(R.id.constraint_layout_volunteer_login_root);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editTextEmail.getText().toString();
                final String password = editTextPassword.getText().toString();
                SubmitLogin submitLogin = new SubmitLogin(context, rootView);
                submitLogin.execute(email, password);
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

    static private class SubmitLogin extends AsyncTask<String, Integer, User> {
        private Context mContext;
        private View rootView;
        ProgressBar progressBar;

        SubmitLogin(Context context, View rootView) {
            this.mContext = context;
            this.rootView = rootView;
        }


        @Override
        protected User doInBackground(String... strings) {

            String email = strings[0];
            String password = strings[1];
            String result = UserLoginDao.loginToAccount(email, password);
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(result);
                User user = new User(jsonObject);
                return user;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            progressBar = rootView.findViewById(R.id.progressBar_volunteer_login);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(User user) {
            if (user == null) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(mContext, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
            } else if (user.getType().equals("company")) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(mContext, "You are in the wrong login screen", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(mContext, VolunteerDashBoard.class);
                intent.putExtra("result", user);
                mContext.startActivity(intent);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProgressBar progressBar = findViewById(R.id.progressBar_volunteer_login);
        progressBar.setVisibility(View.GONE);
    }
}
