package com.ranaturker.task3_part2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _randomNumber = MutableLiveData<Int>()
    val randomNumber: LiveData<Int> get() = _randomNumber

    private val _randomChar = MutableLiveData<Char>()
    val randomChar: LiveData<Char> get() = _randomChar

    fun generateRandomValues() {
        // Random sayı üret
        _randomNumber.value = (1..10).random()

        // Random harf üret
        _randomChar.value = ('A'..'Z').random()
    }

    fun checkGuess(guess: Int): Boolean {
        return guess == _randomNumber.value
    }
}

