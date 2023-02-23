package com.example.spinnergame.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.spinnergame.model.Result

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: Result)

    @Update
    suspend fun updateResult(result: Result)

    @Delete
    suspend fun deleteResult(result: Result)

    @Query("SELECT * FROM result ORDER BY id DESC")
    fun getAllResults(): LiveData<List<Result>>


}