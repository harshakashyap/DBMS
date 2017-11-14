package com.example.kashyap.dbms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DBHelper DBH;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DBH = new DBHelper(this);
        username = (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.pword);
    }

    public void Register(View view) {
        Intent intent = new Intent(LoginActivity.this,Register.class);
        startActivity(intent);

    }

    public void Login(View view) {
        if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty())
            Toast.makeText(this, "Please enter all values for login", Toast.LENGTH_SHORT).show();
        else{
            if(username.getText().toString().equals("admin") && password.getText().toString().equals("21"))
            {
                Intent intent = new Intent(LoginActivity.this,AdminLogin.class);
                startActivity(intent);
            }
            if(DBH.ifUserExists(username.getText().toString(),password.getText().toString()))
            {
                //Toast.makeText(this, "The user exists", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,UserLoginSuccess.class);
                intent.putExtra("Phone",DBH.retrivePhone(username.getText().toString()));
                startActivity(intent);
            }
            else
                Toast.makeText(this, "Access Denied", Toast.LENGTH_SHORT).show();
        }
    }
}
