package com.sanalkasif.dailyhoroscopeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanalkasif.dailyhoroscopeapp.R
import kotlinx.android.synthetic.main.activity_menu.*

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        cardv1.setOnClickListener(){
            val intent = Intent(this, HoroscopeOptionsActivity::class.java)
            startActivity(intent)
            finish()
        }
        cardv2.setOnClickListener(){
            val intent = Intent(this, HoroscopeTagMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        cardv3.setOnClickListener(){
            val intent = Intent(this, HoroscopeFindActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}