package com.example.android.todolistmanager;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> ToDoList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            String[] values = savedInstanceState.getStringArray("myKey");
            if (values != null) {
                ToDoList = new ArrayList<String>(Arrays.asList(values));
            }
        } else {
            ToDoList = new ArrayList<String>();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(ToDoList);
        mRecyclerView.setAdapter(mAdapter);

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        //Called when the user taps the Send button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = (EditText) findViewById(R.id.msg);
                String message = editText.getText().toString();
                ToDoList.add(message);
                mAdapter.notifyItemInserted(ToDoList.size() - 1);
                editText.setText("");
            }
        });


    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//
//        super.onSaveInstanceState(savedInstanceState);
//        String[] values = new String[msgAdapter.getCount()];
//        for (int i = 0; i < msgAdapter.getCount(); i++) {
//            values[i] = msgAdapter.getItem(i);
//        }
//        savedInstanceState.putStringArray("myKey", values);
//
//    }
}

