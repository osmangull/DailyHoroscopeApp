package com.sanalkasif.dailyhoroscopeapp.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sanalkasif.dailyhoroscope.viewmodel.MainViewModel
import com.sanalkasif.dailyhoroscopeapp.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel: MainViewModel
    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()

        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        var cName = GET.getString("burcAdi", "aslan")?.toLowerCase()
        viewmodel.refreshData(cName!!)

        getLiveData()


    }

    private fun getLiveData() {

        viewmodel.horoscope_data.observe(this, Observer { data ->
            data?.let {
                Log.i("deneme",data[0].gunlukYorum)
                setHoroscopeImage(data[0].burc);
                horoscope_name.text = data[0].burc
                element.text = data[0].elementi
                gezegen.text = data[0].gezegeni
                motto.text = data[0].mottosu

                if (!data[0].gunlukYorum.equals("")){//gunluk yorum haricinde dolu gelmez
                    yorum.text = data[0].gunlukYorum
                }else{
                    yorum.text =data[0].yorum
                }
            }
        })

        viewmodel.horoscope_error.observe(this, Observer { error ->
            error?.let {
                if (error) {
                    Log.i("deneme","error")

                } else {
                    Log.i("deneme","noterror")

                }
            }
        })

        viewmodel.horoscope_loading.observe(this, Observer { loading ->
            loading?.let {
                if (loading) {
                    Log.i("deneme","load")

                } else {
                    Log.i("deneme","notload")

                }
            }
        })

    }

    private fun setHoroscopeImage(horoscopeName:String){
        when(horoscopeName.lowercase(Locale.getDefault())){
            "aslan" ->horoscope_img.setImageResource(R.drawable.leo)
            "yengec" ->horoscope_img.setImageResource(R.drawable.cancer)
            "boga" -> horoscope_img.setImageResource(R.drawable.taurus)
            "balık" -> horoscope_img.setImageResource(R.drawable.pisces)
            "kova" -> horoscope_img.setImageResource(R.drawable.aquarius)
            "oglak" -> horoscope_img.setImageResource(R.drawable.capricorn)
            "koc" -> horoscope_img.setImageResource(R.drawable.aries)
            "ikizler" -> horoscope_img.setImageResource(R.drawable.gemini)
            "terazi" -> horoscope_img.setImageResource(R.drawable.libra)
            "yay" -> horoscope_img.setImageResource(R.drawable.sagittarius)
            "akrep" -> horoscope_img.setImageResource(R.drawable.scorpio)
            "basak" -> horoscope_img.setImageResource(R.drawable.virgo)
            else ->{
                horoscope_img.setImageResource(R.drawable.virgo)
            }
        }
    }
}