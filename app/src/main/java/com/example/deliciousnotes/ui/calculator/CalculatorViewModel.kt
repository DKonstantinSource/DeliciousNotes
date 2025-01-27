package com.example.deliciousnotes.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val _result = MutableLiveData<String>().apply {
        value = "Результат: 0 ккал"
    }
    val result: LiveData<String> = _result

    fun resultCount(p: Int, f: Int, c: Int) {
        val calories = (p * 4) + (f * 9) + (c * 4)
        _result.value = "Результат: $calories ккал."
    }
}