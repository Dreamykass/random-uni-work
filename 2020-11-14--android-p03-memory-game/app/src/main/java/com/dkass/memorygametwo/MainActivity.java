package com.dkass.memorygametwo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        for (Level level : levels) {
            Button button = new Button(this);
            button.setText(level.title);
            button.setOnClickListener((View v) -> {
//                Intent intent = new Intent(this, );
            });
            binding.linearLayoutLevelSelect.addView(button);
        }


    }
}