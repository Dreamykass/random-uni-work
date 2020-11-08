package com.dkass.memorygame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.dkass.memorygame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val loader = this::class.java.classLoader!!
        val levelsResource = loader.getResource("/raw/levels.xml")!!
        val levelsString = levelsResource.readText()
//        val levels = levelsFromXmlString(levelsString)


        for (i in 0..10) {
            val butt = Button(this);
            butt.text = "hello"
            butt.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            butt.setOnClickListener {
                val intent = Intent(this, GameActivity::class.java).apply {
                    // putExtra("level id", level_id)
                }
                startActivity(intent);
            }

            binding.linearLayoutLevelSelect.addView(butt);
        }
    }
}