package com.example.android.todolistmanager;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> ToDoList;
    private int position;

    public static class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnCreateContextMenuListener {

        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
            v.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, v.getId(), 0, "Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    //TODO delete somehow from ToDoList
                    return true;
                }
            });
        }


    }

    public void setPosition(int position) {
        this.position = position;
    }

    public MyAdapter(List<String> list) {
        this.ToDoList = list;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(position % 2 == 0) {
            holder.mTextView.setBackgroundColor(Color.RED);
        } else {
            holder.mTextView.setBackgroundColor(Color.BLUE);
        }

        holder.mTextView.setText(ToDoList.get(position));
    }

    @Override
    public int getItemCount() {
        return ToDoList.size();
    }

}