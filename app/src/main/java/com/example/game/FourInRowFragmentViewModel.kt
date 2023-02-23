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
            checkPlayerOne()
            checkPlayerOneReverse()
            namePlayerOne
        } else {
            changeStatus()
            if (listCustom[1] != -1) {
                listChoosePlayerTwo.add(listCustom)
            }
            resetListCustom()
            checkPlayerTowVertical()
            checkPlayerTowHorizontal()
            checkPlayerTwo()
            checkPlayerTwoReverse()
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

    private fun checkPlayerOne() {
        val size = 4
        var sum = 0
        var row = 0
        var culonm = 0
        var flag = true
        for (i in 0..size * 2) {
            var rowCustom = row
            var columnCustom = culonm
            while (rowCustom <= size) {
                if (arrayListOf(columnCustom, rowCustom) in listChoosePlayerOne) {
                    sum++
                    if (sum == size) {
                        player.value = "$namePlayerOne Win"
                    }
                } else {
                    sum = 0
                }
                columnCustom++
                rowCustom++
            }
            if (flag) {
                row++
            } else {
                culonm++
            }
            if (row == size) {
                flag = false
                row = 0
            }
        }

    }

    private fun checkPlayerTwo() {
        val size = 4
        var sum = 0
        var row = 0
        var culonm = 0
        var flag = true
        for (i in 0..size * 2) {
            var rowCustom = row
            var columnCustom = culonm
            while (rowCustom <= size) {
                if (arrayListOf(columnCustom, rowCustom) in listChoosePlayerTwo) {
                    sum++
                    if (sum == size) {
                        player.value = "$namePlayerTwo Win"
                    }
                } else {
                    sum = 0
                }
                columnCustom++
                rowCustom++
            }
            if (flag) {
                row++
            } else {
                culonm++
            }
            if (row == size) {
                flag = false
                row = 0
            }
        }
    }

    private fun checkPlayerOneReverse() {
        val size = 4
        var sum = 0
        var row = size
        var culonm = size
        var flag = true
        for (i in 0..size * 2) {
            var rowCustom = row
            var columnCustom = culonm
            while (rowCustom <= 4) {
                if (arrayListOf(columnCustom, rowCustom) in listChoosePlayerOne) {
                    sum++
                    if (sum == size) {
                        player.value = "$namePlayerOne Win"
                    }
                } else {
                    sum = 0
                }
                columnCustom--
                rowCustom++
            }
            if (flag) {
                row--
            } else {
                culonm--
            }
            if (row == 0) {
                flag = false
            }
        }
    }

    private fun checkPlayerTwoReverse() {
        val size = 4
        var sum = 0
        var row = size
        var culonm = size
        var flag = true
        for (i in 0..size * 2) {
            var rowCustom = row
            var columnCustom = culonm
            while (rowCustom <= 4) {
                if (arrayListOf(columnCustom, rowCustom) in listChoosePlayerTwo) {
                    sum++
                    if (sum == size) {
                        player.value = "$namePlayerTwo Win"
                    }
                } else {
                    sum = 0
                }
                columnCustom--
                rowCustom++
            }
            if (flag) {
                row--
            } else {
                culonm--
            }
            if (row == 0) {
                flag = false
            }
        }
    }


    private fun resetListCustom() {
        listCustom = arrayListOf(-1, -1)

    }
}