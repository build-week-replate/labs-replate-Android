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
import com.example.replate.daos.UserLoginDao;
import com.example.replate.models.Business;
import com.example.replate.models.OfficeLocation;
import com.example.replate.models.User;

import org.json.JSONException;
import org.json.JSONObject;

public class BusinessSignup extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextPassword1;
    EditText editTextPassword2;
    EditText editTextOfficeAddress;
    EditText editTextOfficeName;
    EditText editTextOfficeEmail;
    Button buttonSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_signup);

        editTextName = findViewById(R.id.editText_business_signup_company_name);
        editTextPhone = findViewById(R.id.editText_business_signup_phone);
        editTextEmail = findViewById(R.id.editText_business_signup_email1);
        editTextPassword1 = findViewById(R.id.editText_business_signup_password1);
        editTextPassword2 = findViewById(R.id.editText_business_signup_password2);
        editTextOfficeName = findViewById(R.id.editText_business_signup_office_name);
        editTextOfficeAddress = findViewById(R.id.editText_business_signup_office_address);
        editTextOfficeEmail = findViewById(R.id.editText_business_signup_email2);
        buttonSignup = findViewById(R.id.button_signup_business_complete_signup);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkFields()) { //runs if all fields are valid
                    final Business business = createBusiness();
                    final OfficeLocation officeLocation = createLocation();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = UserLoginDao.createNewAccount(business);
                            Intent intent = new Intent(getApplicationContext(), BusinessDashboard.class);
                            JSONObject jsonObject;
                            try {
                                jsonObject = new JSONObject(result);
                                final User user = new User(jsonObject);
                                new Thread(new Runnable() { //this thread will add the company location in the background
                                    @Override
                                    public void run() {
                                        UserLoginDao.createNewLocation(officeLocation, user.getToken());
                                    }
                                }).start();
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

    public boolean checkFields() {
        if (editTextName.getText().toString().equals(""))
            Toast.makeText(BusinessSignup.this, "Please Enter a Name", Toast.LENGTH_SHORT).show();
        else if (editTextPhone.getText().toString().equals(""))
            Toast.makeText(BusinessSignup.this, "Please Enter a Phone Number", Toast.LENGTH_SHORT).show();
        else if (editTextEmail.getText().toString().equals(""))
            Toast.makeText(BusinessSignup.this, "Please Enter a company Email", Toast.LENGTH_SHORT).show();
        else if (editTextPassword1.getText().toString().equals(""))
            Toast.makeText(BusinessSignup.this, "Please Enter a Password", Toast.LENGTH_SHORT).show();
        else if (!editTextPassword2.getText().toString().equals(editTextPassword1.getText().toString()))
            Toast.makeText(BusinessSignup.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
        else if (editTextOfficeName.getText().toString().equals(""))
            Toast.makeText(BusinessSignup.this, "Please Enter an Office Name", Toast.LENGTH_SHORT).show();
        else if (editTextOfficeAddress.getText().toString().equals(""))
            Toast.makeText(BusinessSignup.this, "Please Enter an Address", Toast.LENGTH_SHORT).show();
        else return true;
        return false;
    }

    private Business createBusiness() {

        return new Business(
                editTextName.getText().toString(),
                Integer.valueOf(editTextPhone.getText().toString()),
                editTextEmail.getText().toString(),
                editTextPassword1.getText().toString(),
                editTextOfficeAddress.getText().toString(),
                editTextOfficeName.getText().toString(),
                editTextOfficeEmail.getText().toString());
    }

    private OfficeLocation createLocation() {

        return new OfficeLocation(
                editTextOfficeName.getText().toString(),
                editTextOfficeAddress.getText().toString(),
                editTextOfficeEmail.getText().toString());
    }
}
