package com.dkass.canvasstuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dkass.canvasstuff.databinding.ActivityCircleBinding;

public class CircleActivity extends AppCompatActivity {
    private ActivityCircleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCircleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button.setOnClickListener((View) -> {
            Intent intent = new Intent(CircleActivity.this, DrawingActivity.class);

            try {
                intent.putExtra("circle", "");
                intent.putExtra("color", binding.editTextTextPersonName3.getText().toString());
                intent.putExtra("cx", Float.parseFloat(binding.editTextNumber7.getText().toString()));
                intent.putExtra("cy", Float.parseFloat(binding.editTextNumber8.getText().toString()));
                intent.putExtra("radius", Float.parseFloat(binding.editTextNumber9.getText().toString()));
            } catch (Exception ignored) {

            }

            CircleActivity.this.startActivity(intent);
        });

    }
}