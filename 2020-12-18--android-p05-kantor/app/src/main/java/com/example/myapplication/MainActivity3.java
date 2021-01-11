package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMain3Binding;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMain3Binding.inflate(getLayoutInflater());
        View view =binding.getRoot();
        setContentView(view);
        binding.button1.setOnClickListener(
                (View v) -> {
                    Intent myIntent = new Intent(MainActivity3.this, MainActivity4.class);
                    try {
                        myIntent.putExtra("rect", "");
                        myIntent.putExtra("color1", binding.editTextTextPersonName2.getText().toString());
                        myIntent.putExtra("left", Float.parseFloat(binding.editTextNumber4.getText().toString()));
                        myIntent.putExtra("top", Float.parseFloat(binding.editTextNumber5.getText().toString()));
                        myIntent.putExtra("right", Float.parseFloat(binding.editTextNumber6.getText().toString()));
                        myIntent.putExtra("bottom", Float.parseFloat(binding.editTextNumber7.getText().toString()));
                        myIntent.putExtra("wypelnienie",(binding.checkBox.isChecked()));

                    } catch (Exception ignored) {

                    }
                    MainActivity3.this.startActivity(myIntent);
                }
        );
    }
}