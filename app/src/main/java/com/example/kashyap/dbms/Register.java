package com.example.kashyap.dbms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText name,phone,email,username, password;
    DBHelper DBH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        DBH = new DBHelper(this);
    }

    public void Register(View view) {
        String sName, sPhone, sEmail, sUsername, sPassword;
        sName = name.getText().toString();
        sPhone = phone.getText().toString();
        sEmail = email.getText().toString();
        sUsername = username.getText().toString();
        sPassword = password.getText().toString();

        if(!sName.isEmpty() && !sPhone.isEmpty() && !sEmail.isEmpty() && !sUsername.isEmpty() && !sPassword.isEmpty()) {
            DBH.registerUser(sUsername, sPassword, sName, sEmail, Integer.parseInt(sPhone));
            Toast.makeText(this, "Record inserted", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Please enter all the required values!", Toast.LENGTH_SHORT).show();
    }
}
