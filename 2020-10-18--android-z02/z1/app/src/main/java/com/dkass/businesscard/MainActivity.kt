package com.dkass.businesscard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonShort.setOnClickListener {
            val intent = Intent(this, ShortActivity::class.java)
            startActivity(intent)
        }

        buttonLong.setOnClickListener {
            val intent = Intent(this, LongActivity::class.java)
            startActivity(intent)
        }

        buttonExit.setOnClickListener {
            finishAffinity()
        }
    }


}