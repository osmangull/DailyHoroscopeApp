package com.sanalkasif.dailyhoroscopeapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanalkasif.dailyhoroscopeapp.R
import kotlinx.android.synthetic.main.activity_horoscope_find.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.horoscope_options_layout.*

class HoroscopeOptionsActivity : AppCompatActivity() {
    private lateinit var GET: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.horoscope_options_layout)
        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        var horoscopeName = GET.getString("horoscopeDefaultName", "")?.toLowerCase()
        var routeStringWeek = "haftalik"
        var routeStringMonth = "aylik"
        var routeStringYear = "yillik"


        cardViewGunluk.setOnClickListener(){
            setMainActivity(horoscopeName.toString(),"")
        }
        cardViewHafta.setOnClickListener(){
            setMainActivity(horoscopeName.toString(),routeStringWeek)
        }
        cardViewAylik.setOnClickListener(){
            setMainActivity(horoscopeName.toString(),routeStringMonth)
        }
        cardViewYil.setOnClickListener(){
            setMainActivity(horoscopeName.toString(),routeStringYear)
        }

    }

    private fun setMainActivity(path : String,time : String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("horoscopeName",path)
        intent.putExtra("horoscopeTime",time)
        startActivity(intent)
        finish()
    }
}