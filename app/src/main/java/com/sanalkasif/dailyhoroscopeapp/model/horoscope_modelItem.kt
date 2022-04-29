package com.sanalkasif.dailyhoroscopeapp.model


import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class horoscope_modelItem(
    @SerializedName("Burc")
    val burc: String,
    @SerializedName("Elementi")
    val elementi: String,
    @SerializedName("Gezegeni")
    val gezegeni: String,
    @SerializedName("GunlukYorum")
    val gunlukYorum: String,
    @SerializedName("Mottosu")
    val mottosu: String,
    @SerializedName("Yorum")
    val yorum: String,
    @SerializedName("Zaman")
    val zaman: String
)