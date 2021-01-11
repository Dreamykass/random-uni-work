package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate((getLayoutInflater()));
        View view = binding.getRoot();
        setContentView(view);
        myDb = new DatabaseHelper(this,"Student.db");


                binding.button1.setOnClickListener(
                        (View v) -> {
                            Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                            MainActivity.this.startActivity(myIntent);
                        });



            binding.button2.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Cursor res = myDb.getAllData();
                            if(res.getCount() == 0) {
                                // show message
                                showMessage("Error","Nothing found");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {

                                buffer.append("Waluta :"+ res.getString(1)+"\n");
                                buffer.append("kurs kupna :"+ res.getString(2)+"\n");
                                buffer.append("Kurs sprzedaży :"+ res.getString(3)+"\n\n");
                            }

                            // Show all data
                            showMessage("Data",buffer.toString());
                        }
                    }
            );



        binding.button3.setOnClickListener(
                (View v) -> {
                    Intent myIntent = new Intent(MainActivity.this, MainActivity4.class);
                    MainActivity.this.startActivity(myIntent);
                });
        binding.button5.setOnClickListener(
                (View v) -> {
                    Intent myIntent = new Intent(MainActivity.this, MainActivity5.class);
                    MainActivity.this.startActivity(myIntent);
                });

    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }}