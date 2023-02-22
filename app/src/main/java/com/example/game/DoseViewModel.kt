package com.example.game


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DoseViewModel : ViewModel() {
    private var statusPlayer = StatusPlayerDose.X
    private var namePlayerOne = "X"
    private var namePlayerTwo = "O"
    val player = MutableLiveData(namePlayerOne)
    private val listChoose = arrayListOf("", "", "", "", "", "", "", "", "")
    val listLiveChoose = MutableLiveData<ArrayList<String>>()
    private val lisPlayerX = mutableSetOf<Int>()
    private val lisPlayerO = mutableSetOf<Int>()
    private val listWinner = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(0, 4, 8),
        listOf(2, 4, 6),
    )

    private fun changeStatus() {
        statusPlayer = if (statusPlayer == StatusPlayerDose.X) {
            player.value = namePlayerTwo
            StatusPlayerDose.O
        } else {
            player.value = namePlayerOne
            StatusPlayerDose.X
        }
    }

    private fun statusRow(): Boolean {
        return (lisPlayerX.size + lisPlayerO.size) == 9
    }

    private fun statusGameX(): StatusGameDose {
        for (list in listWinner) {
            var count = 0
            for (int in list) if (int in lisPlayerX) {
                count++
            }
            if (count == 3) {
                return StatusGameDose.XWIN
            }
        }
        if (statusRow()) return StatusGameDose.ROW
        return StatusGameDose.NONE
    }

    private fun statusGameO(): StatusGameDose {
        for (list in listWinner) {
            var count = 0
            for (int in list) if (int in lisPlayerO) {
                count++
            }
            if (count == 3) {
                return StatusGameDose.OWIN
            }
        }
        if (statusRow()) return StatusGameDose.ROW
        return StatusGameDose.NONE
    }

    fun choose(number: Int): StatusGameDose {
        val status: StatusGameDose
        if (statusPlayer == StatusPlayerDose.X) {
            lisPlayerX.add(number)
            listChoose[number] = "X"
            status = statusGameX()
        } else {
            lisPlayerO.add(number)
            listChoose[number] = "O"
            status = statusGameO()
        }
        listLiveChoose.value = listChoose
        changeStatus()
        when (status) {
            StatusGameDose.XWIN -> player.value = "$namePlayerOne Win"
            StatusGameDose.OWIN -> player.value = "$namePlayerTwo Win"
            StatusGameDose.ROW -> player.value = "Row"
            else -> {}
        }
        return status
    }

    fun reset() {
        lisPlayerX.clear()
        lisPlayerO.clear()
        player.value = namePlayerOne
        statusPlayer = StatusPlayerDose.X
        for (i in listChoose.indices) listChoose[i] = ""
        listLiveChoose.value = listChoose
    }

    fun checkChoose(number: Int): Boolean {
        return number in lisPlayerX || number in lisPlayerO


    }
}