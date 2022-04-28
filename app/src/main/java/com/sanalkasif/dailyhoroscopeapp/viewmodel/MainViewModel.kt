package com.sanalkasif.dailyhoroscope.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanalkasif.dailyhoroscopeapp.model.horoscope_model
import com.sanalkasif.dailyhoroscopeapp.service.HoroscopeApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    private val horoscopeApiService = HoroscopeApiService()
    private val disposable = CompositeDisposable()

    val horoscope_data = MutableLiveData<horoscope_model>()
    val horoscope_error = MutableLiveData<Boolean>() // if this value is false// observer run and error screen is active
    val horoscope_loading = MutableLiveData<Boolean>()

    fun refreshData(horoscopeName: String) {
        getDataFromAPI(horoscopeName)
    }

    private fun getDataFromAPI(horoscopeName: String) {

        horoscope_loading.value = true
        disposable.add(
            horoscopeApiService.getData(horoscopeName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<horoscope_model>() {

                    override fun onSuccess(t: horoscope_model) {
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