package com.dkass.wheeloffortune;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dkass.wheeloffortune.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        AnswerService.init();

        binding.button.setOnClickListener(
                (View v) -> {
                    Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                    MainActivity.this.startActivity(myIntent);
                }
        );

    }
}