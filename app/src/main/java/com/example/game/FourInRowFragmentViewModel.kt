package com.example.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FourInRowFragmentViewModel : ViewModel() {

    private var listPlace = ArrayList<String>()
    private var rowAndColumn = 5
    fun changeRowAndColumn(createRowAndColumn: Int) {
        rowAndColumn = createRowAndColumn
    }

    private fun fetchData(createRowAndColumn: Int) {
        for (i in 0 until createRowAndColumn * createRowAndColumn) {
            listPlace.add("")
        }
    }

    init {
        fetchData(rowAndColumn)
    }

    private val listChoosePlayerOne = mutableSetOf<Int>()
    private val listChoosePlayerTwo = mutableSetOf<Int>()
    private var statusPlayer = StatusPlayerFourRow.PLAYER1
    private var namePlayerOne = "pooria"
    private var namePlayerTwo = "mohsen"
    val player = MutableLiveData(namePlayerOne)
    var status = arrayListOf(false)

    fun getListPlace(): ArrayList<String> {
        return listPlace
    }

    private fun changeStatus() {
        statusPlayer = if (statusPlayer == StatusPlayerFourRow.PLAYER1) {
            player.value = namePlayerTwo
            StatusPlayerFourRow.PLAYER2
        } else {
            player.value = namePlayerOne
            StatusPlayerFourRow.PLAYER1
        }
    }

    fun choosePlace(place: Int): ArrayList<String> {
        var location = place % rowAndColumn + ((rowAndColumn - 1) * rowAndColumn)
        for (index in 0 until rowAndColumn) {
            if (listPlace[location] == "") {
                listPlace[location] = choose(location)
                break
            } else location -= rowAndColumn
        }
        return listPlace
    }

    private fun choose(place: Int): String {
        return if (statusPlayer == StatusPlayerFourRow.PLAYER1) {
            changeStatus()
            listChoosePlayerOne.add(place)
            if (check(listChoosePlayerOne)) {
                status[0] = true
                player.value = "$namePlayerOne Win"
            }
            namePlayerOne
        } else {
            changeStatus()
            listChoosePlayerTwo.add(place)
            if (check(listChoosePlayerTwo)) {
                status[0] = true
                player.value = "$namePlayerTwo Win"
            }
            namePlayerTwo
        }
    }

    private fun check(listChoose: MutableSet<Int>): Boolean {
        return if (winVertical(listChoose)) {
            true
        } else if (winHorizontal(listChoose)) {
            true
        }else if(winDiagonal(listChoose)){
            true
        } else winDiagonalRevers(listChoose)
    }

    private fun winVertical(listChoose: MutableSet<Int>): Boolean {
        for (i in 0 until rowAndColumn) {
            var count = 0
            for (j in i until rowAndColumn * rowAndColumn step 5) {
                if (j in listChoose) {
                    count++
                    if (count == 4) return true
                } else {
                    count = 0
                }
            }
        }
        return false
    }


    private fun winHorizontal(listChoose: MutableSet<Int>): Boolean {
        for (i in 0 until rowAndColumn * rowAndColumn step 5) {
            var count = 0
            for (j in i..i + 4) {
                if (j in listChoose) {
                    count++
                    if (count == 4) return true
                } else {
                    count = 0
                }
            }
        }
        return false
    }

    private fun winDiagonal(listChoose: MutableSet<Int>): Boolean {
        for (i in 0 until rowAndColumn * rowAndColumn) {
            var count = 0
            var step = rowAndColumn
            var num = 0
            var number: Int
            for (_i in 0 until rowAndColumn * 2 - 1) {
                if (num >= rowAndColumn * (rowAndColumn - 1)) {
                    step = 1
                }
                number = num
                for (j in 0..num / (rowAndColumn - 1)) {
                    if (number in listChoose) {
                        count++
                        if (count == 4) {
                            return true
                        }
                    } else {
                        count = 0
                    }
                    number -= rowAndColumn - 1
                }
                num += step
            }
        }
        return false
    }

    private fun winDiagonalRevers(listChoose: MutableSet<Int>): Boolean {
        for (i in 0 until rowAndColumn * rowAndColumn) {
            var count = 0
            var step = rowAndColumn
            var num = rowAndColumn - 1
            var number: Int
            for (_i in 0 until rowAndColumn * 2 - 1) {
                if (num >= rowAndColumn * (rowAndColumn - 1)) {
                    step = -1
                }
                number = num
                for (j in 0..num / (rowAndColumn - 1)) {
                    if (number in listChoose) {
                        count++
                        if (count == 4) {
                            return true
                        }
                    } else {
                        count = 0
                    }
                    number -= rowAndColumn + 1
                }
                num += step
            }
        }
        return false
    }
}