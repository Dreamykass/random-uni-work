package com.dkass.memorygametwo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dkass.memorygametwo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<Level> levels = LevelsLoader.loadAllLevels(this);

//        for (Level level : levels) {
//            Button button = new Button(this);
//            button.setText(level.title);
//            button.setOnClickListener((View v) -> {
//                Intent intent = new Intent(MainActivity.this, GameActivity.class);
//                intent.putExtra("level_title", level.title);
//                MainActivity.this.startActivity(intent);
//            });
//            binding.linearLayoutLevelSelect.addView(button);
//        }

        binding.buttonStart.setOnClickListener((View v) -> {
            int startingN = Integer.parseInt(binding.editNumberFirst.getText().toString());
            int decreaseN = Integer.parseInt(binding.editNumberDecrease.getText().toString());

            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("thisN", Integer.toString(startingN));
            intent.putExtra("decreaseN", Integer.toString(decreaseN));
            intent.putExtra("loaded", false);
            MainActivity.this.startActivity(intent);
        });

        binding.buttonLoad.setOnClickListener((View v) -> {
            int startingN = Integer.parseInt(binding.editNumberFirst.getText().toString());
            int decreaseN = Integer.parseInt(binding.editNumberDecrease.getText().toString());

            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("thisN",
                    Integer.toString(sharedPref.getInt("thisN", -1)));
            intent.putExtra("decreaseN",
                    Integer.toString(sharedPref.getInt("decreaseN", -1)));
            intent.putExtra("loaded", true);
            MainActivity.this.startActivity(intent);

        });

    }
}