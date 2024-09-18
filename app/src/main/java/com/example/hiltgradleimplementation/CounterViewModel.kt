package com.example.hiltgradleimplementation

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> get() = _counter

    private val counterKey = "counter"

    init {
        loadCounter()
    }

    // Cargar el contador desde SharedPreferences
    private fun loadCounter() {
        val currentCounter = sharedPreferences.getInt(counterKey, 0)
        _counter.value = currentCounter
    }

    // Incrementar y guardar el contador en SharedPreferences
    fun incrementCounter() {
        val currentCounter = _counter.value ?: 0
        val newCounter = currentCounter + 1
        _counter.value = newCounter
        sharedPreferences.edit().putInt(counterKey, newCounter).apply()
    }
}
