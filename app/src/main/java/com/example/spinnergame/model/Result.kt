package com.example.spinnergame.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "result")
data class Result(
    val result: String,
    val data:Int,
    val time: String,
   val color:Int,
    @PrimaryKey(autoGenerate = true)
val id: Int=0
)
