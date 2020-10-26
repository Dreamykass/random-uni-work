package com.dkass.canvasstuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dkass.canvasstuff.databinding.ActivityRectBinding;

public class RectActivity extends AppCompatActivity {
    private ActivityRectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRectBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonNext.setOnClickListener((View) -> {
            Intent intent = new Intent(RectActivity.this, DrawingActivity.class);

            try {
                intent.putExtra("rect", "");
                intent.putExtra("color1", binding.editTextTextPersonName.getText().toString());
                intent.putExtra("color2", binding.editTextTextPersonName4.getText().toString());
                intent.putExtra("left", Float.parseFloat(binding.editTextNumber.getText().toString()));
                intent.putExtra("top", Float.parseFloat(binding.editTextNumber2.getText().toString()));
                intent.putExtra("right", Float.parseFloat(binding.editTextNumber3.getText().toString()));
                intent.putExtra("bottom", Float.parseFloat(binding.editTextNumber4.getText().toString()));
            } catch (Exception ignored) {

            }

            RectActivity.this.startActivity(intent);
        });
    }
}