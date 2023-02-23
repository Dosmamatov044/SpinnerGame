package com.example.spinnergame.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spinnergame.model.Result

@Database(
    entities = [Result::class],
    version = 1, exportSchema = false
)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun toResultDao(): ResultDao

}