package com.example.replate.activities.Signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.replate.R;
import com.example.replate.adapters.NetworkAdapter;
import com.example.replate.daos.BusinessLoginDao;
import com.example.replate.models.Business;

public class BusinessSignup extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextPassword1;
    EditText editTextPassword2;
    EditText editTextAddress;
    EditText editTextOfficeName;
    EditText editTextOfficeEmail;
    Button signup;


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
        editTextAddress = findViewById(R.id.editText_business_signup_office_address);
        editTextOfficeEmail = findViewById(R.id.editText_business_signup_email2);
        signup = findViewById(R.id.button_signup_business_complete_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkFields()) {
                    final Business business = createBusiness();

                    new Thread(new Runnable() { //run this if all fields are valid
                        @Override
                        public void run() {
                            BusinessLoginDao.createNewAccount(business);
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
        else if (editTextAddress.getText().toString().equals(""))
            Toast.makeText(BusinessSignup.this, "Please Enter an Address", Toast.LENGTH_SHORT).show();
        else return true;
        return false;
    }

    private Business createBusiness() {

        return new Business(
                editTextName.getText().toString(),
                editTextPhone.getText().toString(),
                editTextEmail.getText().toString(),
                editTextAddress.getText().toString(),
                editTextOfficeName.getText().toString(),
                editTextOfficeEmail.getText().toString());
    }
}
