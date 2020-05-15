package com.marcus.demo_database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lvData;
    SQLiteDatabase db;
    ListAdapter listAdapter;
    DBHelper dbTask;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lvData = findViewById(R.id.lv);
        dbTask = new DBHelper(getApplicationContext());
        db = dbTask.getReadableDatabase();
        cursor = dbTask.getAllDataForList();
        listAdapter = new ListAdapter(getApplicationContext(), R.layout.row);
        lvData.setAdapter(listAdapter);

        if (cursor.moveToFirst()) {
            do {
                String desc, date;
                Integer id;
                id = cursor.getInt(0);
                desc = cursor.getString(1);
                date = cursor.getString(2);


                Task task = new Task(id, desc, date);
                listAdapter.add(task);

            }
            while (cursor.moveToNext());
        }
        listAdapter.notifyDataSetChanged();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();
                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                dbTask = new DBHelper(getApplicationContext());

                cursor = dbTask.getAllDataForList();
                listAdapter = new ListAdapter(getApplicationContext(), R.layout.row);
                lvData.setAdapter(listAdapter);
                if (cursor.moveToFirst()) {
                    do {
                        String desc, date;
                        Integer id;
                        id = cursor.getInt(0);
                        desc = cursor.getString(1);
                        date = cursor.getString(2);


                        Task task = new Task(id, desc, date);
                        listAdapter.add(task);

                    }
                    while (cursor.moveToNext());
                }
                listAdapter.notifyDataSetChanged();
                tvResults.setText(txt);

            }
        });
    }
}