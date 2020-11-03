package com.dkass.memorygame

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dkass.memorygame.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        for (i in 0..10) {
            var butt = Button(this)
            butt.text = "hello"
            binding.gridLayout.addView(butt)
        }

    }
}