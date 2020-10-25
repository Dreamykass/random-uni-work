package com.dkass.canvasstuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dkass.canvasstuff.databinding.ActivityTriangleBinding;

public class TriangleActivity extends AppCompatActivity {
    private ActivityTriangleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTriangleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonNext3.setOnClickListener((View) -> {
            Intent myIntent = new Intent(TriangleActivity.this, DrawingActivity.class);
            TriangleActivity.this.startActivity(myIntent);
        });
    }

}