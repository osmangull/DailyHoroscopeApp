package com.sanalkasif.dailyhoroscopeapp.service
import com.sanalkasif.dailyhoroscopeapp.model.horoscope_model
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HoroscopeApiService {

    private val BASE_URL = "https://burc-yorumlari.herokuapp.com/get/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(HoroscopeApi::class.java)

    fun getData(burcAdi: String): Single<horoscope_model> {
        return api.getData(burcAdi)
    }

}