package com.dkass.canvasstuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dkass.canvasstuff.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonRect.setOnClickListener((View) -> {
            Intent myIntent = new Intent(MainActivity.this, RectActivity.class);
            MainActivity.this.startActivity(myIntent);
        });

        binding.buttonTriangle.setOnClickListener((View) -> {
            Intent myIntent = new Intent(MainActivity.this, TriangleActivity.class);
            MainActivity.this.startActivity(myIntent);
        });

        binding.buttonText.setOnClickListener((View) -> {
            Intent myIntent = new Intent(MainActivity.this, TextActivity.class);
            MainActivity.this.startActivity(myIntent);
        });

        binding.button2.setOnClickListener((View) -> {
            Intent myIntent = new Intent(MainActivity.this, CircleActivity.class);
            MainActivity.this.startActivity(myIntent);
        });
    }

}