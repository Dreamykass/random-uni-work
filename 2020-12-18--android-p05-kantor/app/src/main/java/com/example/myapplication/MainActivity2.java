package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplication.databinding.ActivityMain2Binding;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myDb;
    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this,"Student.db");

        binding = ActivityMain2Binding.inflate((getLayoutInflater()));
        View view = binding.getRoot();
        setContentView(view);

        Cursor res = myDb.getAllData();


        Map<String, Float> list  = new HashMap<>();
        Map<String, Float> list2  = new HashMap<>();

        while (res.moveToNext()) {
           // buffer.append("Id :"+ res.getString(0)+"\n");
          //  buffer.append("Name :"+ res.getString(1)+"\n");
           // buffer.append("Surname :"+ res.getString(2)+"\n");
           // buffer.append("Marks :"+ res.getString(3)+"\n\n");
           // list.put(res.getString(1), res.getFloat(2));
            list.put(res.getString(1), res.getFloat(2));
            list2.put(res.getString(1), res.getFloat(3));
        }

        Spinner spinner = findViewById(R.id.spinner1);
        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.waluty, android.R.layout.simple_spinner_item);*/

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,list.keySet().toArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

       // spinner.setOnItemSelectedListener(this);

        binding.button3.setOnClickListener(
                (View v) -> {
                    Float a = list.get(spinner.getSelectedItem()) * Float.valueOf(binding.editTextNumber2.getText().toString());

                    binding.textView4.setText(list.get(spinner.getSelectedItem()).toString());

                    binding.textView12.setText(a.toString());
                });

        binding.button.setOnClickListener(
                (View v) -> {

                    Float b = Float.valueOf(binding.editTextNumber.getText().toString()) *  list2.get(spinner.getSelectedItem());

                    binding.textView4.setText(list.get(spinner.getSelectedItem()).toString());

                    binding.textView17.setText(b.toString());
                });

        binding.button9.setOnClickListener(
                (View v) -> {


                    binding.textView4.setText(list.get(spinner.getSelectedItem()).toString());
                    binding.textView14.setText(list2.get(spinner.getSelectedItem()).toString());



                });


    }
}