package com.sanalkasif.dailyhoroscopeapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanalkasif.dailyhoroscopeapp.R
import kotlinx.android.synthetic.main.activity_horoscope_tag_menu.*

class HoroscopeTagMenuActivity : AppCompatActivity() {
    private lateinit var GET: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope_tag_menu)
        GET = getSharedPreferences(packageName, MODE_PRIVATE)


        ask.setOnClickListener(){
            initTagDetailActivity("ask")
        }
        kariyer.setOnClickListener(){
            initTagDetailActivity("kariyer")
        }
        saglik.setOnClickListener(){
            initTagDetailActivity("saglik")
        }
        stil.setOnClickListener(){
            initTagDetailActivity("stil")
        }
        kadini.setOnClickListener(){
            initTagDetailActivity("kadini")
        }
        erkegi.setOnClickListener(){
            initTagDetailActivity("erkegi")
        }
        olumlu.setOnClickListener(){
            initTagDetailActivity("olumlu-yonler")
        }
        zitlik.setOnClickListener(){
            initTagDetailActivity("zit-burclari")
        }
        diyet.setOnClickListener(){
            initTagDetailActivity("diyet")
        }
        eglence.setOnClickListener(){
            initTagDetailActivity("eglence-hayati")
        }
    }

    private fun initTagDetailActivity(tag :String){
        var horoscopeName = GET.getString("horoscopeDefaultName", "")?.toLowerCase()
        val intent = Intent(this, TagDetailActivity::class.java)
        intent.putExtra("horoscopeName",horoscopeName)
        intent.putExtra("horoscopeTag",tag)
        startActivity(intent)

    }
}