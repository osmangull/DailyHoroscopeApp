package com.sanalkasif.dailyhoroscopeapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sanalkasif.dailyhoroscope.viewmodel.MainViewModel

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
}