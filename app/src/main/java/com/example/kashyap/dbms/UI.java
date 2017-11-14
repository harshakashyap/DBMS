package com.example.kashyap.dbms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UI extends AppCompatActivity {

    public DBHelper DBH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        DBH = new DBHelper(this);
    }

    public void Login(View view) {
        Intent intent = new Intent(UI.this,LoginActivity.class);
        startActivity(intent);
    }
}
