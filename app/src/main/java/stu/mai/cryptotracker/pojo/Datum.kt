package stu.mai.cryptotracker.pojo

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Datum (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo:CoinInfo? = null
)