package com.example.dice_roll.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiceViewModel : ViewModel() {
    private val _diceRoll = MutableLiveData<Int>()
    val diceRoll: LiveData<Int> = _diceRoll

    private val _player1Points = MutableLiveData<Int>(0)
    val player1Points: LiveData<Int> = _player1Points

    private val _player2Points = MutableLiveData<Int>(0)
    val player2Points: LiveData<Int> = _player2Points

    fun rollDice(player: Int) {
        val result = (1..6).random()
        _diceRoll.value = result
        if (player == 1) {
            _player1Points.value = _player1Points.value?.plus(result)
        } else {
            _player2Points.value = _player2Points.value?.plus(result)
        }
    }

    fun resetGame() {
        _player1Points.value = 0
        _player2Points.value = 0
    }
}