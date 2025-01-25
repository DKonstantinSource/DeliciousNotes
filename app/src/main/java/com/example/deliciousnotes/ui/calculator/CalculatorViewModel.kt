package com.example.deliciousnotes.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Valera , za spiskom nastanet tvoe vremya"
    }
    val text: LiveData<String> = _text
}