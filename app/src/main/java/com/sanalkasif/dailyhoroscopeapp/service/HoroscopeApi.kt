package com.sanalkasif.dailyhoroscopeapp.service
import com.sanalkasif.dailyhoroscopeapp.model.horoscope_model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApi {

    @GET("{horoscopeName}")
    fun getData(
        @Path("horoscopeName") horoscopeName: String
    ): Single<horoscope_model>

}