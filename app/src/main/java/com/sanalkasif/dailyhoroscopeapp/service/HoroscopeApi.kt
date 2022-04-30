package com.sanalkasif.dailyhoroscopeapp.service
import com.sanalkasif.dailyhoroscopeapp.model.horoscope_model
import com.sanalkasif.dailyhoroscopeapp.model.horoscope_tag_model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApi {

    @GET("/get/{horoscopeName}")
    fun getData(
        @Path("horoscopeName") horoscopeName: String
    ): Single<horoscope_model>


    @GET("/get/{horoscopeName}/{time}")
    fun getDataTime(
        @Path("horoscopeName") horoscopeName: String, @Path("time") time :String
    ): Single<horoscope_model>


    @GET("/gets/{horoscopeName}/{tag}")
    fun getHoroscopeTagData(
        @Path("horoscopeName") horoscopeName: String, @Path("tag") time :String
    ): Single<horoscope_tag_model>

}