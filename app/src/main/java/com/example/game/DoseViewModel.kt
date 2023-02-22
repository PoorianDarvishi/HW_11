package com.example.game


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DoseViewModel : ViewModel() {
    private var statusPlayer = StatusPlayer.X
    private var namePlayerX = "X"
    private var namePlayerO = "O"
    val player = MutableLiveData(namePlayerX)
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
        statusPlayer = if (statusPlayer == StatusPlayer.X){
            player.value = namePlayerO
            StatusPlayer.O
        }
        else {
            player.value = namePlayerX
            StatusPlayer.X
        }
    }

    private fun statusRow(): Boolean {
        return (lisPlayerX.size + lisPlayerO.size) == 9
    }

    private fun statusGameX(): StatusGame {
    for (list in listWinner) {
        var count = 0
        for (int in list)
            if (int in lisPlayerX) {
                count++
            }
        if (count == 3) {
            return StatusGame.XWIN
        }
    }
    if (statusRow()) return StatusGame.ROW
    return StatusGame.NONE
}

    private fun statusGameO(): StatusGame {
        for (list in listWinner) {
            var count = 0
            for (int in list)
                if (int in lisPlayerO) {
                    count++
                }
            if (count == 3) {
                return StatusGame.OWIN
            }
        }
        if (statusRow()) return StatusGame.ROW
        return StatusGame.NONE
    }

    fun choose(number: Int): StatusGame {
        val status: StatusGame
        if (statusPlayer == StatusPlayer.X) {
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
            StatusGame.XWIN -> player.value = "$namePlayerX Win"
            StatusGame.OWIN -> player.value = "$namePlayerO Win"
            StatusGame.ROW -> player.value = "Row"
            else -> {}
        }
        return status
    }
    fun reset() {
        lisPlayerX.clear()
        lisPlayerO.clear()
        player.value = namePlayerX
        statusPlayer = StatusPlayer.X
        for (i in listChoose.indices) listChoose[i] = ""
        listLiveChoose.value = listChoose
    }
    fun checkChoose(number: Int): Boolean{
        return  number in lisPlayerX || number in lisPlayerO


    }
}


//

//

//

//

//

//

//

//


