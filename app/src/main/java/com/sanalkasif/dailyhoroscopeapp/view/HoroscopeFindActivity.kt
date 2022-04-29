package com.sanalkasif.dailyhoroscopeapp.view

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sanalkasif.dailyhoroscopeapp.R
import kotlinx.android.synthetic.main.activity_horoscope_find.*

class HoroscopeFindActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope_find)
        button.setOnClickListener(){
            horoscope_txt.text = horoscopeCalculate(datePicker1.dayOfMonth,datePicker1.month);
            horoscope_txt.visibility = View.VISIBLE
        }
    }

    private fun horoscopeCalculate(day : Int, month:Int):String{

        when (month) {
            1 -> {
                return if (day<=21){
                    "Oglak";
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
                    "Balik"
                }else{
                    "Hatali giris!"
                }

            }
            3 -> {
                return if (day<=20){
                    "Balik";
                }else if (day <= 31){
                    "Koc"
                }else{
                    "Hatali giris!"
                }
            }
            4 -> {
                return if (day<=20){
                    "Koc";
                }else if (day <= 30){
                    "Boga"
                }else{
                    "Hatali giris!"
                }

            }
            5 -> {
                return if (day<=20){
                    "Boga";
                }else if (day <= 30){
                    "Ikizler"
                }else{
                    "Hatali giris!"
                }

            }
            6 -> {
                return if (day<=22){
                    "Ikizler";
                }else if (day <= 30){
                    "Yengec"
                }else{
                    "Hatali giris!"
                }
            }
            7 -> {
                return if (day<=22){
                    "Yengec";
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
                    "Basak"
                }else{
                    "Hatali giris!"
                }
            }
            9 -> {
                return if (day<=22){
                    "Basak";
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
                    "Oglak"
                }else{
                    "Hatali giris!"
                }
            }
            else -> System.out.println("Hatali giris!")
        };


        return "burc";
    }
}