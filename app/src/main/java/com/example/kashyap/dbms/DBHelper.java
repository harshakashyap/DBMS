package com.example.kashyap.dbms;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

/**
 * Created by kashyap on 7/11/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context,"Warehouse", null, 1);
    }
    static int TID = 21108;
    Random random;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Login ( Username text primary key, Password text, Phone integer ) ");
        sqLiteDatabase.execSQL("create table Customers ( Name text, Phone INTEGER references Login(Phone), Email text ) "); //Check for errors here
        sqLiteDatabase.execSQL("create table RAM (RAMno text, Manufacturer text, Size text, Type text, Cost integer, Stock integer ) ");
        sqLiteDatabase.execSQL("create table Transactions (TransactID text primary key autoincrement, Username text, ProductID text)");
        sqLiteDatabase.execSQL("create table Processor (ProcNo text primary key, Manufacturer text, Cores integer, Clock text, Cost integer, Stock integer) ");
        sqLiteDatabase.execSQL("create table Motherboard (MBNo text primary key, Manufacturer text, Cost integer, Stock integer) ");

        init(sqLiteDatabase);

    }

    private void init(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Insert into RAM values('CMX8GX3M2A1600C11', 'Corsair', '8GB', 'DDR3', 7479, 26 )");
        sqLiteDatabase.execSQL("Insert into RAM values('CMK16GX4M1A2400C14', 'Corsair', '16GB', 'DDR4', 14300, 35 )");
        sqLiteDatabase.execSQL("Insert into RAM values('F4-3000C16D-16GTZR', 'G.Skill Trident', '16GB', 'DDR4', 17300, 20 )");
        sqLiteDatabase.execSQL("Insert into RAM values('HX432C16PB3K4/16', 'Kingston', '16GB', 'DDR4', 26333, 21 )");
        sqLiteDatabase.execSQL("Insert into RAM values('F4-3000C15D-16GVR', 'G.Skill Ripjaws', '16GB', 'DDR4', 22453, 13 )");

        sqLiteDatabase.execSQL("Insert into Processor values ('i7 6950X', 'Intel', 10, '3.00Ghz', 145000, 12)");
        sqLiteDatabase.execSQL("Insert into Processor values ('i3 8100', 'Intel', 4, '3.60Ghz', 10499, 5)");
        sqLiteDatabase.execSQL("Insert into Processor values ('Ryzen 5 1500x', 'AMD', 4, '3.50Ghz', 15450, 20)");

        sqLiteDatabase.execSQL("Insert into Motherboard values ('GA-H110M-S2', 'GIGABYTE', 4343, 50) ");
        sqLiteDatabase.execSQL("Insert into Motherboard values ('GA-B250M-DS3H', 'GIGABYTE', 7080, 81) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Login");
        sqLiteDatabase.execSQL("drop table if exists Customers");
        sqLiteDatabase.execSQL("drop table if exists RAM");
        sqLiteDatabase.execSQL("drop table if exists Transactions");
        sqLiteDatabase.execSQL("drop table if exists Processor");
        sqLiteDatabase.execSQL("drop table if exists Motherboard");
        onCreate(sqLiteDatabase);
    }

    public boolean ifUserExists(String username,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("Select * from Login where Username = '"+username+"' and Password = '"+password+"' ",null);
        if(cursor!=null && cursor.getCount()!=0)
            return true;
        else return false;
    }

    public void registerUser(String username, String password, String name, String email, int phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("insert into Login values ( '"+username+"', '"+password+"', "+phone+" ) ");
        db.execSQL("insert into Customers values ( '"+name+"', '"+phone+"', '"+email+"' ) ");
    }

    public Cursor dispRAM()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" Select * from RAM ",null);
        return cursor;
    }

    public String retrivePhone(String s) {
        Cursor cursor;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("Select Phone from Login where Username = '"+s+"' ",null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public void updateStock(String TableName, String phone, String PKval, String PKname) {
        SQLiteDatabase db1 = this.getReadableDatabase();
        random = new Random();
        int flag=0;
        int flag2 = 0;
        Cursor cursor = db1.rawQuery("Select Stock from "+TableName+" where "+PKname+" = '"+PKval+"' ",null);
        cursor.moveToFirst();
        int stock = Integer.parseInt(cursor.getString(0));
        cursor = db1.rawQuery("Select Username from Login where Phone = "+phone+" ",null);
        cursor.moveToFirst();
        String username = cursor.getString(0);
        flag = random.nextInt();
        cursor = db1.rawQuery("Select TransactID from Transactions ",null);
        cursor.moveToFirst();
        while (flag<cursor.getCount())
        {
            if(cursor.getString(0).equals(TID)) {   //flag2 = 1;
                TID++;
            }
            flag++;
        }
        db1.close();
        //if(flag2==0)
        //{
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("Update "+TableName+" set Stock = "+(stock-1)+" where "+PKname+" = '"+PKval+"' ");
            //db.execSQL("Insert into Transactions values( '"+TID+"', '"+username+"', '"+PKval+"' )");  Clear this bug
            db.close();
        //}

    }

    public Cursor dispProc() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" Select * from Processor ",null);
        return cursor;
    }

    public Cursor dispMB() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" Select * from Motherboard ",null);
        return cursor;
    }
}
