package com.sanalkasif.dailyhoroscopeapp.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sanalkasif.dailyhoroscopeapp.R
import kotlinx.android.synthetic.main.activity_horoscope_find.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

class HoroscopeFindActivity : AppCompatActivity() {

    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope_find)
        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()
        calendar = Calendar.getInstance()
        datePicker1.maxDate= calendar.timeInMillis

        button.setOnClickListener(){
            horoscope_txt.text = horoscopeCalculate(datePicker1.dayOfMonth,datePicker1.month+1);
            horoscope_txt.visibility = View.VISIBLE
            kaydet.visibility = View.VISIBLE
        }

        kaydet.setOnClickListener(){
            SET.putString("horoscopeDefaultName", horoscope_txt.text.toString())
            SET.apply()
            Toast.makeText(applicationContext, "Kaydediliyor...", Toast.LENGTH_SHORT).show()
            kaydet.visibility = View.GONE
            Handler().postDelayed({
                Toast.makeText(applicationContext, "Hadi Başlayalım!", Toast.LENGTH_SHORT).show()
                horoscopeMenu.visibility = View.VISIBLE
                val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.alpha)
                horoscopeMenu.startAnimation(animationFadeIn)
                horoscopeMenu.setOnClickListener(){
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("horoscopeName",horoscope_txt.text.toString())
                    intent.putExtra("horoscopeTime","")
                    startActivity(intent)
                    finish()
                }
            }, 2500)
        }
    }

    private fun horoscopeCalculate(day : Int, month:Int):String{

        when (month) {
            1 -> {
                return if (day<=21){
                    "Oğlak";
                }else if (day <= 31){
                    "Kova"
                }else{
                    "Hatali giris!"
                }
            }
            2 -> {
                return if (day<=19){
                    "Kova";
                }else if (day <= 29){
                    "Balık"
                }else{
                    "Hatali giris!"
                }

            }
            3 -> {
                return if (day<=20){
                    "Balık";
                }else if (day <= 31){
                    "Koç"
                }else{
                    "Hatali giris!"
                }
            }
            4 -> {
                return if (day<=20){
                    "Koç";
                }else if (day <= 30){
                    "Boğa"
                }else{
                    "Hatali giris!"
                }

            }
            5 -> {
                return if (day<=21){
                    "Boğa";
                }else if (day <= 30){
                    "İkizler"
                }else{
                    "Hatali giris!"
                }

            }
            6 -> {
                return if (day<=22){
                    "İkizler";
                }else if (day <= 30){
                    "Yengeç"
                }else{
                    "Hatali giris!"
                }
            }
            7 -> {
                return if (day<=22){
                    "Yengeç";
                }else if (day <= 31){
                    "Aslan"
                }else{
                    "Hatali giris!"
                }
            }
            8 -> {
                return if (day<=22){
                    "Aslan";
                }else if (day <= 31){
                    "Başak"
                }else{
                    "Hatali giris!"
                }
            }
            9 -> {
                return if (day<=22){
                    "Başak";
                }else if (day <= 30){
                    "Terazi"
                }else{
                    "Hatali giris!"
                }
            }
            10 -> {
                return if (day<=22){
                    "Terazi";
                }else if (day <= 31){
                    "Akrep"
                }else{
                    "Hatali giris!"
                }
            }
            11 -> {
                return if (day<=21){
                    "Akrep";
                }else if (day <= 30){
                    "Yay"
                }else{
                    "Hatali giris!"
                }

            }
            12 -> {
                return if (day<=21){
                    "Yay";
                }else if (day <= 30){
                    "Oğlak"
                }else{
                    "Hatali giris!"
                }
            }
            else -> System.out.println("Hatali giris!")
        };


        return "burc";
    }
}