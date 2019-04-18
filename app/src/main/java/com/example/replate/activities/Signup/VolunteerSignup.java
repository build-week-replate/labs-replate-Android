package com.example.replate.activities.Signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.replate.R;
import com.example.replate.activities.Dashboard.BusinessDashboard;
import com.example.replate.activities.Dashboard.VolunteerDashBoard;
import com.example.replate.daos.UserLoginDao;
import com.example.replate.models.Business;
import com.example.replate.models.User;
import com.example.replate.models.Volunteer;

import org.json.JSONException;
import org.json.JSONObject;

public class VolunteerSignup extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextPassword1;
    EditText editTextPassword2;
    Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_signup);

        editTextName = findViewById(R.id.editText_volunteer_signup_name);
        editTextPhone = findViewById(R.id.editText_volunteer_signup_phone);
        editTextEmail = findViewById(R.id.editText_volunteer_signup_email);
        editTextPassword1 = findViewById(R.id.editText_volunteer_signup_password1);
        editTextPassword2 = findViewById(R.id.editText_volunteer_signup_password2);
        buttonSignup = findViewById(R.id.button_signup_volunteer_complete_signup);


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkFields()) { //runs if all fields are valid
                    final Volunteer volunteer = createVolunteer();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = UserLoginDao.createNewAccount(volunteer);
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
            }
        });


    }

    private Volunteer createVolunteer() {

        return new Volunteer(
                editTextName.getText().toString(),
                Integer.valueOf(editTextPhone.getText().toString()),
                editTextEmail.getText().toString(),
                editTextPassword1.getText().toString());
    }


    public boolean checkFields() {
        if (editTextName.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Please Enter a Name", Toast.LENGTH_SHORT).show();
        else if (editTextPhone.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Please Enter a Phone Number", Toast.LENGTH_SHORT).show();
        else if (editTextEmail.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Please Enter a company Email", Toast.LENGTH_SHORT).show();
        else if (editTextPassword1.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Please Enter a Password", Toast.LENGTH_SHORT).show();
        else if (!editTextPassword2.getText().toString().equals(editTextPassword1.getText().toString()))
            Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
        else return true;
        return false;
    }
}
