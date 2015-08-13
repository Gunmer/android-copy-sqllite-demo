package com.innocv.copysqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.main_text);

        DataBaseHelper myDbHelper = new DataBaseHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        MySqliteHelper mySqliteHelper = new MySqliteHelper(this);
        SQLiteDatabase readableDatabase = mySqliteHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.rawQuery("Select * from ATMS", null);

        String message = "Atms Count" + cursor.getCount();

        text.setText(message);
    }

}
