package com.sanalkasif.dailyhoroscopeapp.service
import com.sanalkasif.dailyhoroscopeapp.model.horoscope_model
import com.sanalkasif.dailyhoroscopeapp.model.horoscope_tag_model
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HoroscopeApiService {

    private val BASE_URL = "https://burc-yorumlari.herokuapp.com"

    val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor) // same for .addInterceptor(...)
        .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(HoroscopeApi::class.java)

    fun getData(horoscopeName: String): Single<horoscope_model> {
        return api.getData(horoscopeName)
    }

    fun getDataWithTime(horoscopeName: String,time:String): Single<horoscope_model> {
        return api.getDataTime(horoscopeName,time)
    }

    fun getDataWithTag(horoscopeName: String,tag : String): Single<horoscope_tag_model> {
        return api.getHoroscopeTagData(horoscopeName,tag)
    }
}