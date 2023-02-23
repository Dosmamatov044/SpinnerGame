package com.example.spinnergame.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.spinnergame.model.Result
import com.example.spinnergame.repository.ResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject
constructor(private val toDoRepository: ResultRepository) : ViewModel() {

    val sumData = MutableLiveData(0)


    fun insertToResult(result: Result) = viewModelScope.launch {
        toDoRepository.insertToResult(result)
    }

    fun deleteToResult(result: Result) = viewModelScope.launch {
        toDoRepository.deleteToResult(result)
    }


    val allToResult = toDoRepository.getAllToResult()


    private fun resultSum() {

        viewModelScope.launch {
            allToResult.asFlow().collect {


                viewModelScope.launch {
                    sum(it).collect { its ->
                        sumData.value = its

                    }

                }


            }

        }


    }


    fun sum(results: List<Result>) = flow {

        var data = 0
        results.forEach {

            data += it.data

        }
        emit(data)
    }

    init {
        resultSum()
    }


}