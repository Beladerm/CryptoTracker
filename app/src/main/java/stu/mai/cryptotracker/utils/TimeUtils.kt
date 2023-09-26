package stu.mai.cryptotracker.utils

import android.annotation.SuppressLint

@SuppressLint("SimpleDateFormat")
fun convertTimestampToTime(timestamp: Long?): String {
    if (timestamp == null) return ""
    val date = java.util.Date(timestamp * 1000L)
    val sdf = java.text.SimpleDateFormat("HH:mm:ss")
    sdf.timeZone = java.util.TimeZone.getTimeZone("GMT+3")
    return sdf.format(date)
}