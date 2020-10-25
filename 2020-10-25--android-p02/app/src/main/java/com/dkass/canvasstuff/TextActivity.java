package com.dkass.canvasstuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dkass.canvasstuff.databinding.ActivityTextBinding;

public class TextActivity extends AppCompatActivity {
    private ActivityTextBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonNext2.setOnClickListener((View) -> {
            Intent intent = new Intent(TextActivity.this, DrawingActivity.class);

            try {
                intent.putExtra("text", binding.editTextTextPersonName2.getText().toString());
                intent.putExtra("x", Float.parseFloat(binding.editTextNumber5.getText().toString()));
                intent.putExtra("y", Float.parseFloat(binding.editTextNumber6.getText().toString()));
            } catch (Exception ignored) {

            }
            TextActivity.this.startActivity(intent);
        });
    }
}