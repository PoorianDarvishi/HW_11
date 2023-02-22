package com.example.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FourInRowFragmentViewModel : ViewModel() {
    private val listPlace = arrayListOf(
        arrayListOf("", "", "", "", ""),
        arrayListOf("", "", "", "", ""),
        arrayListOf("", "", "", "", ""),
        arrayListOf("", "", "", "", ""),
        arrayListOf("", "", "", "", ""),
    )
    private val listChoosePlayerOne = mutableSetOf<ArrayList<Int>>()
    val listChoosePlayerTwo = mutableSetOf<ArrayList<Int>>()
    var listCustom = arrayListOf(-1, -1)
    val listPlaceLiveData = MutableLiveData(listPlace)

    private var statusPlayer = StatusPlayerFourRow.PLAYER1
    private var namePlayerOne = "pooria"
    private var namePlayerTwo = "mohsen"
    val player = MutableLiveData(namePlayerOne)
    private fun changeStatus() {
        statusPlayer = if (statusPlayer == StatusPlayerFourRow.PLAYER1) {
            player.value = namePlayerTwo
            StatusPlayerFourRow.PLAYER2
        } else {
            player.value = namePlayerOne
            StatusPlayerFourRow.PLAYER1
        }
    }

    fun chooseColumn(column: Int) {
        listCustom[0] = column
        listPlace[column] = chooseInColumn(listPlace[column])
        listPlaceLiveData.value = listPlace
    }

    private fun chooseInColumn(list: ArrayList<String>): ArrayList<String> {
        for (i in list.indices) {
            if (list[i] == "") {
                listCustom[1] = i
                list[i] = choose()
                return list
            }
        }
        return list
    }

    private fun choose(): String {
        return if (statusPlayer == StatusPlayerFourRow.PLAYER1) {
            changeStatus()
            if (listCustom[1] != -1) {
                listChoosePlayerOne.add(listCustom)
            }
            resetListCustom()
            checkPlayerOneVertical()
            checkPlayerOneHorizontal()
            namePlayerOne
        } else {
            changeStatus()
            if (listCustom[1] != -1) {
                listChoosePlayerTwo.add(listCustom)
            }
            resetListCustom()
            checkPlayerTowVertical()
            checkPlayerTowHorizontal()
            namePlayerTwo
        }
    }

    private fun checkPlayerOneVertical() {
        var sum = 0
        for (i in 0 until listPlace.size) {
            for (j in 0 until listPlace[0].size) {
                if (arrayListOf(i, j) in listChoosePlayerOne) {
                    sum++
                    if (sum == 4) {
                        player.value = "$namePlayerOne Win"
                    }
                } else {
                    sum = 0
                }
            }
            sum = 0
        }
    }

    private fun checkPlayerOneHorizontal() {
        var sum = 0
        for (i in 0 until listPlace.size) {
            for (j in 0 until listPlace[0].size) {
                if (arrayListOf(j, i) in listChoosePlayerOne) {
                    sum++
                    if (sum == 4) {
                        player.value = "$namePlayerOne Win"
                    }
                } else {
                    sum = 0
                }
            }
            sum = 0
        }
    }

    private fun checkPlayerTowVertical() {
        var sum = 0
        for (i in 0 until listPlace.size) {
            for (j in 0 until listPlace[0].size) {
                if (arrayListOf(i, j) in listChoosePlayerTwo) {
                    sum++
                    if (sum == 4) {
                        player.value = "$namePlayerTwo Win"
                    }
                } else {
                    sum = 0
                }
            }
            sum = 0
        }
    }

    private fun checkPlayerTowHorizontal() {
        var sum = 0
        for (i in 0 until listPlace.size) {
            for (j in 0 until listPlace[0].size) {
                if (arrayListOf(j, i) in listChoosePlayerTwo) {
                    sum++
                    if (sum == 4) {
                        player.value = "$namePlayerTwo Win"
                    }
                } else {
                    sum = 0
                }
            }
            sum = 0
        }
    }



    private fun resetListCustom() {
        listCustom = arrayListOf(-1, -1)

    }
}