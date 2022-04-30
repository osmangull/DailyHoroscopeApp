package com.sanalkasif.dailyhoroscopeapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanalkasif.dailyhoroscopeapp.model.horoscope_model
import com.sanalkasif.dailyhoroscopeapp.model.horoscope_tag_model
import com.sanalkasif.dailyhoroscopeapp.service.HoroscopeApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 *Created by OsmanGul
 */
private const val TAG = "TagDataViewModel"

class TagDataViewModel : ViewModel() {

    private val horoscopeApiService = HoroscopeApiService()
    private val disposable = CompositeDisposable()

    val horoscope_data = MutableLiveData<horoscope_tag_model>()
    val horoscope_error = MutableLiveData<Boolean>() // if this value is false// observer run and error screen is active
    val horoscope_loading = MutableLiveData<Boolean>()

    fun refreshData(horoscopeName: String,tag : String) {
            getTagDataHoroscope(horoscopeName,tag)
    }


    private fun getTagDataHoroscope(horoscopeName: String, tag:String) {

        horoscope_loading.value = true
        disposable.add(
            horoscopeApiService.getDataWithTag(horoscopeName,tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<horoscope_tag_model>() {

                    override fun onSuccess(t: horoscope_tag_model) {
                        horoscope_data.value = t
                        horoscope_error.value = false
                        horoscope_loading.value = false
                        Log.d(TAG, "onSuccess: Success")
                    }

                    override fun onError(e: Throwable) {
                        horoscope_error.value = true
                        horoscope_loading.value = false
                        Log.e(TAG, "onError: " + e)
                    }

                })
        )

    }


}