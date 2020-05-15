package com.marcus.demo_database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public ListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView ID, DESC, DATE;
    }
    @Override
    public void add(@Nullable Object object) {
//        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.DESC=(TextView)row.findViewById(R.id.tvDesc);
            layoutHandler.ID=(TextView)row.findViewById(R.id.tvID);
            layoutHandler.DATE=(TextView)row.findViewById(R.id.tvDate);
            row.setTag(layoutHandler);
        }
        else{
            layoutHandler = (LayoutHandler) row.getTag();
        }
        Task foodListAdapter = (Task) this.getItem(position);
        layoutHandler.DESC.setText(foodListAdapter.getDescription());
        layoutHandler.ID.setText(Integer.toString(foodListAdapter.getId()));
        layoutHandler.DATE.setText(foodListAdapter.getDate());


        return row;
    }
}