package com.example.game

import android.util.Log
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
    fun chooseColumn(column : Int){
        listPlace[column] = chooseInColumn(listPlace[column])
        listPlaceLiveData.value = listPlace
    }

    private fun chooseInColumn(list: ArrayList<String>): ArrayList<String> {
        for (i in list.indices) {
            if (list[i] == "") {
                list[i] = choose()
                return list
            }
        }
        return list
    }
    private fun choose(): String{
        return  if(statusPlayer == StatusPlayerFourRow.PLAYER1) {
            changeStatus()
            namePlayerOne
        }else{
            changeStatus()
            namePlayerTwo
        }
    }

}