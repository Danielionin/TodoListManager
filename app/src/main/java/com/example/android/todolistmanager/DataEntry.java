package com.example.android.todolistmanager;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by daniel on 17/04/17.
 */

public class DataEntry implements Serializable {

    private String task;
    private Calendar date;

    public DataEntry(String s, Calendar c){
        task = s;
        date = c;
    }

    public String getTask() {
        return task;
    }

    public Calendar getDate() {
        return date;
    }
}
