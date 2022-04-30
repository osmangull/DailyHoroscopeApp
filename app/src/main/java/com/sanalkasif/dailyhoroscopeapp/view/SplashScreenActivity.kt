package com.sanalkasif.dailyhoroscopeapp.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.sanalkasif.dailyhoroscopeapp.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var GET: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        StartAnimations()
    }

    private fun StartAnimations() {
        var horoscopeName = GET.getString("horoscopeDefaultName", "")?.toLowerCase()
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.alpha)
        horoscope.startAnimation(animationFadeIn)
        Handler().postDelayed({
            if(horoscopeName.equals("")){
                val intent = Intent(this, HoroscopeFindActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("horoscopeName",horoscopeName)
                intent.putExtra("horoscopeTime","")
                startActivity(intent)
                finish()
            }


        }, 2000)
    }
}