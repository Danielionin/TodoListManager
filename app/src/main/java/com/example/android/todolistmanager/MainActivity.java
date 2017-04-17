package com.example.android.todolistmanager;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String DATA = "data";

    ArrayList<DataEntry> data;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        if (savedInstanceState == null) {
            data = new ArrayList<>();
        }
        else {
            data = (ArrayList<DataEntry>) savedInstanceState.getSerializable(DATA);

        }

        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //Called when the user taps the Send button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter();
            }
        });
    }

    private void enter() {
        new AlertDialog.Builder(this).setView(
                getLayoutInflater().inflate(R.layout.add_task, null))
                .setTitle("Add New Item")
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        EditText editText = (EditText) ((android.app.AlertDialog) dialog).findViewById(R.id.editText);
                        DatePicker datePicker = (DatePicker) ((android.app.AlertDialog) dialog).findViewById(R.id.datePicker);

                        String text = editText.getText().toString();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(datePicker.getYear(),
                                datePicker.getMonth(),
                                datePicker.getDayOfMonth()
                        );

                        data.add(new DataEntry(text, calendar));

                        Collections.sort(data, new Comparator<DataEntry>() {
                            @Override
                            public int compare(DataEntry o1, DataEntry o2) {
                                return o1.getDate().compareTo(o2.getDate());
                            }
                        });

                        adapter.notifyDataSetChanged();

                    }
                }).setNegativeButton("Cancel", null).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(DATA, data);

    }
}

