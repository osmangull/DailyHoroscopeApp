package com.sanalkasif.dailyhoroscopeapp.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.sanalkasif.dailyhoroscope.viewmodel.MainViewModel
import com.sanalkasif.dailyhoroscopeapp.R
import com.sanalkasif.dailyhoroscopeapp.helpers.CircularProgress
import kotlinx.android.synthetic.main.activity_horoscope_find.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel: MainViewModel
    private var cp: CircularProgress? = null
    private lateinit var SET: SharedPreferences.Editor
    private lateinit var GET: SharedPreferences
    private var mInterstitialAd: InterstitialAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val horoscopeName=intent.getStringExtra("horoscopeName")
        val horoscopTime=intent.getStringExtra("horoscopeTime")
        cp = CircularProgress(this);
        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()


        if (horoscopTime.toString().equals("")) {
            viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            viewmodel.refreshData(horoscopeName!!,"")

        } else{
            viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            viewmodel.refreshData(horoscopeName!!,horoscopTime.toString())

        }
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-5073720258631379/6773839442", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("errorMain", adError?.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("loadMain", "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })

        Handler().postDelayed({
            Log.i("asdasd",mInterstitialAd.toString()+"")
            mInterstitialAd?.show(this)
        }, 4000)

        menu_icon.setOnClickListener(){
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
        getLiveData()

    }

    private fun getLiveData() {

        viewmodel.horoscope_data.observe(this, Observer { data ->
            data?.let {
                setHoroscopeImage(data[0].burc);
                horoscope_name.text = data[0].burc
                element.text = data[0].elementi
                gezegen.text = data[0].gezegeni
                motto.text = data[0].mottosu

                if (data[0].gunlukYorum != null && !data[0].gunlukYorum.equals("")){//gunluk yorum haricinde dolu gelmez
                    yorum.text = data[0].gunlukYorum
                    SET.putString("dailyHoroscope", data[0].gunlukYorum)
                    SET.apply()
                }else{
                    yorum.text =data[0].yorum
                }
            }
        })

        viewmodel.horoscope_error.observe(this, Observer { error ->
            error?.let {
                if (error) {

                } else {

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
            "aslan" ->horoscope_img.setImageResource(R.drawable.leo)
            "yengeç" ->horoscope_img.setImageResource(R.drawable.cancer)
            "boğa" -> horoscope_img.setImageResource(R.drawable.taurus)
            "balık" -> horoscope_img.setImageResource(R.drawable.pisces)
            "kova" -> horoscope_img.setImageResource(R.drawable.aquarius)
            "oğlak" -> horoscope_img.setImageResource(R.drawable.capricorn)
            "koç" -> horoscope_img.setImageResource(R.drawable.aries)
            "ikizler" -> horoscope_img.setImageResource(R.drawable.gemini)
            "terazi" -> horoscope_img.setImageResource(R.drawable.libra)
            "yay" -> horoscope_img.setImageResource(R.drawable.sagittarius)
            "akrep" -> horoscope_img.setImageResource(R.drawable.scorpio)
            "başak" -> horoscope_img.setImageResource(R.drawable.virgo)
            else ->{
                horoscope_img.setImageResource(R.drawable.virgo)
            }
        }
    }
}