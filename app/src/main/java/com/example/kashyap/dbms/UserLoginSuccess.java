package com.example.kashyap.dbms;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class UserLoginSuccess extends AppCompatActivity {

    RadioGroup RG;
    RadioButton RB;
    ArrayList AL;
    RecyclerView RV;
    DBHelper DBH;
    RAM_Adapter RAMadapter;
    Proc_Adapter ProcAdapter;
    MB_Adapter MBadapter;
    int ch;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_success);
        Intent intent = getIntent();
        phone = intent.getStringExtra("Phone");

        RG = (RadioGroup) findViewById(R.id.RG);
        DBH = new DBHelper(this);
        RG.clearCheck();
        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RB = (RadioButton) findViewById(i);
                if(RB!=null && i>-1){
                    displayItems(RB.getText().toString());
                }
            }
        });
    }

    private void displayItems(String s) {

        Cursor cursor;
        RV = (RecyclerView) findViewById(R.id.RV);
        if(s.equals("RAM"))
        {
            AL = new ArrayList<RAMdata>();
            cursor = DBH.dispRAM();
            cursor.moveToFirst();
            int i=0;
            while(i<cursor.getCount())
            {
                RAMdata R = new RAMdata();
                R.RAMno = cursor.getString(cursor.getColumnIndex("RAMno"));
                R.Manufacturer = cursor.getString(cursor.getColumnIndex("Manufacturer"));
                R.Stock = cursor.getString(cursor.getColumnIndex("Stock"));
                R.Cost = cursor.getString(cursor.getColumnIndex("Cost"));
                R.Type = cursor.getString(cursor.getColumnIndex("Type"));
                R.Size = cursor.getString(cursor.getColumnIndex("Size"));

                AL.add(R);
                cursor.moveToNext();
                i++;
            }
            RAMadapter = new RAM_Adapter(AL, new RAM_Adapter.Myownclicklistener() {
                @Override
                public void onItemclick(int position) {
                    RAMdata R1 = (RAMdata) AL.get(position);
                    DBH.updateStock("RAM",phone,R1.RAMno, "RAMno");
                    Toast.makeText(UserLoginSuccess.this, "Your RAM has been purchased", Toast.LENGTH_SHORT).show();
                }
            });
            RV.setHasFixedSize(true);
            RV.setLayoutManager(new LinearLayoutManager(this));
            RV.setAdapter(RAMadapter);

        }
        else if (s.equals("Processor"))
        {
            AL = new ArrayList();
            cursor = DBH.dispProc();
            cursor.moveToFirst();
            int i=0;
            while (i<cursor.getCount())
            {
                ProcData P = new ProcData();
                P.ProcNo = cursor.getString(cursor.getColumnIndex("ProcNo"));
                P.Manufacturer = cursor.getString(cursor.getColumnIndex("Manufacturer"));
                P.Stock = cursor.getString(cursor.getColumnIndex("Stock"));
                P.Cores = cursor.getString(cursor.getColumnIndex("Cores"));
                P.Clock = cursor.getString(cursor.getColumnIndex("Clock"));
                P.Cost = cursor.getString(cursor.getColumnIndex("Cost"));

                AL.add(P);
                cursor.moveToNext();
                i++;
            }
            ProcAdapter = new Proc_Adapter(AL, new Proc_Adapter.Myownclicklistener2() {
               @Override
               public void onItemclick(int position) {
                   ProcData P1 = (ProcData) AL.get(position);
                   DBH.updateStock("Processor",phone,P1.ProcNo,"ProcNo");
                   Toast.makeText(UserLoginSuccess.this, "Your Processor has been purchased", Toast.LENGTH_SHORT).show();
               }
           });
            RV.setHasFixedSize(true);
            RV.setLayoutManager(new LinearLayoutManager(this));
            RV.setAdapter(ProcAdapter);
        }
        else  if(s.equals("Motherboard"))
        {
            AL = new ArrayList();
            cursor = DBH.dispMB();
            cursor.moveToFirst();
            int i=0;
            while (i<cursor.getCount())
            {
                MBdata M = new MBdata();
                M.Cost = cursor.getString(cursor.getColumnIndex("Cost"));
                M.Stock = cursor.getString(cursor.getColumnIndex("Stock"));
                M.Manufacturer = cursor.getString(cursor.getColumnIndex("Manufacturer"));
                M.MBno = cursor.getString(cursor.getColumnIndex("MBNo"));

                AL.add(M);
                cursor.moveToNext();
                i++;
            }
            MBadapter = new MB_Adapter(AL, new MB_Adapter.Myownclicklistener3() {
                @Override
                public void onItemclick(int position) {
                    MBdata M1 = (MBdata) AL.get(position);
                    DBH.updateStock("Motherboard",phone,M1.MBno,"MBNo");
                    Toast.makeText(UserLoginSuccess.this, "Your Motherboard has been purchased", Toast.LENGTH_SHORT).show();
                }
            });
            RV.setHasFixedSize(true);
            RV.setLayoutManager(new LinearLayoutManager(this));
            RV.setAdapter(MBadapter);
        }
    }
}
