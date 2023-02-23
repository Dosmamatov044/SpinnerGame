package com.example.spinnergame.helpers

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentTime():String{
    val now = Calendar.getInstance().time
    val formatter=SimpleDateFormat("HH:mm:ss")
    val formatNow=formatter.format(now)

    return formatNow
}