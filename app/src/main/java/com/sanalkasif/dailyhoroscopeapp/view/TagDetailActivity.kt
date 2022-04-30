package com.sanalkasif.dailyhoroscopeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sanalkasif.dailyhoroscope.viewmodel.MainViewModel
import com.sanalkasif.dailyhoroscopeapp.R
import com.sanalkasif.dailyhoroscopeapp.helpers.CircularProgress
import com.sanalkasif.dailyhoroscopeapp.viewmodel.TagDataViewModel
import kotlinx.android.synthetic.main.activity_main.yorum
import kotlinx.android.synthetic.main.activity_tag_detail.*
import java.util.*

class TagDetailActivity : AppCompatActivity() {

    private lateinit var viewmodel: TagDataViewModel
    private var cp: CircularProgress? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_detail)
        cp = CircularProgress(this);

        val horoscopeName=intent.getStringExtra("horoscopeName")
        val horoscopeTag=intent.getStringExtra("horoscopeTag")
        viewmodel = ViewModelProviders.of(this).get(TagDataViewModel::class.java)

        viewmodel.refreshData(horoscopeName!!,horoscopeTag.toString())

        getLiveData()

    }


    private fun getLiveData() {

        viewmodel.horoscope_data.observe(this, Observer { data ->
            data?.let {
                setHoroscopeImage(data[0].burc)
                tagTitle.text = data[0].ozellik
                baslik.text = data[0].baslik
                yorum.text = data[0].yorum

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
                    cp?.ShowCircularProgress()

                } else {
                    cp?.DismissCircularProgress()

                }
            }
        })

    }

    private fun setHoroscopeImage(horoscopeName:String){
        when(horoscopeName.lowercase(Locale.getDefault())){
            "aslan" ->image_horoscope.setImageResource(R.drawable.leo)
            "yengeç" ->image_horoscope.setImageResource(R.drawable.cancer)
            "boğa" -> image_horoscope.setImageResource(R.drawable.taurus)
            "balık" -> image_horoscope.setImageResource(R.drawable.pisces)
            "kova" -> image_horoscope.setImageResource(R.drawable.aquarius)
            "oğlak" -> image_horoscope.setImageResource(R.drawable.capricorn)
            "koç" -> image_horoscope.setImageResource(R.drawable.aries)
            "ikizler" -> image_horoscope.setImageResource(R.drawable.gemini)
            "terazi" -> image_horoscope.setImageResource(R.drawable.libra)
            "yay" -> image_horoscope.setImageResource(R.drawable.sagittarius)
            "akrep" -> image_horoscope.setImageResource(R.drawable.scorpio)
            "başak" -> image_horoscope.setImageResource(R.drawable.virgo)
            else ->{
                image_horoscope.setImageResource(R.drawable.virgo)
            }
        }
    }
}