package com.sanalkasif.dailyhoroscopeapp.model


import com.google.gson.annotations.SerializedName

data class horoscope_tag_modelItem(
    @SerializedName("Baslik")
    val baslik: String,
    @SerializedName("Burc")
    val burc: String,
    @SerializedName("Ozellik")
    val ozellik: String,
    @SerializedName("Unluler")
    val unluler: String,
    @SerializedName("Yorum")
    val yorum: String
)