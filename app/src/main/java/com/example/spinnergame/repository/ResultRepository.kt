package com.example.spinnergame.repository

import com.example.spinnergame.db.ResultDao
import com.example.spinnergame.model.Result
import javax.inject.Inject

class ResultRepository@Inject
constructor(private val toResult: ResultDao) {

    suspend fun insertToResult(result: Result) = toResult.insertResult(result)
    suspend fun deleteToResult(result: Result) = toResult.deleteResult(result)
    fun getAllToResult() = toResult.getAllResults()

}