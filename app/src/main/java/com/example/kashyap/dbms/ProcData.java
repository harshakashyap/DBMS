package com.example.kashyap.dbms;

/**
 * Created by kashyap on 12/11/17.
 */

class ProcData {
    public String ProcNo, Manufacturer, Cores, Clock, Cost, Stock;

    public ProcData(String a, String b, String c, String d, String e, String f)
    {
        ProcNo = a;
        Manufacturer = b;
        Cores = c;
        Clock = d;
        Cost = e;
        Stock = f;
    }

    public ProcData() {
    }
}
