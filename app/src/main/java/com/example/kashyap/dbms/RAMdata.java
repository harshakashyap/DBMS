package com.example.kashyap.dbms;

/**
 * Created by kashyap on 9/11/17.
 */

public class RAMdata {
    public String RAMno, Manufacturer, Size, Type, Cost, Stock;

    public RAMdata(String a, String b, String c, String d, String e, String f)
    {
        RAMno = a;
        Manufacturer = b;
        Size = c;
        Type = d;
        Cost = e;
        Stock = f;
    }

    public RAMdata() {
    }
}