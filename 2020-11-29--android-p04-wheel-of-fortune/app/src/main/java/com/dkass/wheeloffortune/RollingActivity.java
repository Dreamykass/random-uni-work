package com.dkass.wheeloffortune;

import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.dkass.wheeloffortune.databinding.ActivityRollingBinding;

public class RollingActivity extends AppCompatActivity {
    int size;
    int currentItem;
    int nextItem;

    float startingAngle;
    float rotateBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRollingBinding binding = ActivityRollingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        size = getIntent().getIntExtra("size", -1000);
        currentItem = getIntent().getIntExtra("currentItem", -1000);
        nextItem = getIntent().getIntExtra("nextItem", -1000);

        startingAngle = ((360f / size) * currentItem);
        rotateBy = ((360f / size) * (size - currentItem)) + ((360f / size) * nextItem);

        binding.imageView.setRotation(startingAngle);

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                binding.imageView.animate().rotationBy(360).withEndAction(this).setDuration(3000).setInterpolator(new DecelerateInterpolator()).start();
//            }
//        };
//        binding.imageView.animate().rotationBy(360).withEndAction(runnable).setDuration(3000).setInterpolator(new LinearInterpolator()).start();

//        binding.imageView.animate().rotationBy(360).setDuration(3000).setInterpolator(new DecelerateInterpolator()).start();

        binding.imageView.setOnClickListener((View v) -> {
            binding.imageView.animate().rotationBy(360 + rotateBy).setDuration(3000).setInterpolator(new DecelerateInterpolator()).start();
        });
    }
}