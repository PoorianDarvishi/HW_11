package com.example.questionplus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    companion object{
        const val TRUE = "true"
        const val FALSE = "false"
    }


    private val listQuestion = listOf(
        "A dog sweats by panting its tongue.",
        "It takes a sloth two weeks to digest a meal.",
        "The largest living frog is the Goliath frog of West Africa.",
        "When exiting a cave, bats always go in the direction of the wind.",
        "Galapagos tortoises sleep up to 16 hours a day.",
        "Infants have more bones than adults.",
        "Humans lose an average of 75 strands of hair a month.",
        "The human body has four lungs.",
        "A monkey was the first non-human to go into space.",
        "The goat is the national animal of Scotland.",
    )
    private val listAnswer = listOf(
        "false",
        "true",
        "true",
        "false",
        "true",
        "true",
        "false",
        "false",
        "false",
        "false",
    )
    private val listChoose = mutableSetOf<String>()
    private val listCheat = mutableSetOf<String>()
    private var numberQuestionAndAnswer = 0
    private var point = 0
    private var negativeNum = 0
    private var statusPoint = false
    val question = MutableLiveData(listQuestion[numberQuestionAndAnswer])
    val answer = MutableLiveData("Are you suer you want to do dis?")
    val pointShow = MutableLiveData("your score is: $point")
    private fun changeQuiz() {
        question.value = listQuestion[numberQuestionAndAnswer]
    }

    fun nextQuestion() {
        if (numberQuestionAndAnswer < listQuestion.size - 1) {
            numberQuestionAndAnswer++
            changeQuiz()
        }
    }


    fun prevQuestion() {
        if (numberQuestionAndAnswer > 0) {
            numberQuestionAndAnswer--
            changeQuiz()
        }
    }
    fun resetShowAnswer(){
        answer.value ="Are you suer you want to do dis?"
    }

    fun showAnswer() {
        addTooCheat()
        answer.value = listAnswer[numberQuestionAndAnswer]
    }

    fun checkAnswer(answer: String): String {
        val result: String = if (checkCheat()) {
            "Cheating is wrong"
        } else {
            when (answer) {
                listAnswer[numberQuestionAndAnswer] -> {
                    statusPoint = true
                    "Correct"
                }
                else -> {
                    statusPoint = false
                    "Incorrect"
                }
            }
        }
        return if (!checkQuestion()) {
            addTooQuestionChoose()
            checkPoint(statusPoint)
            showPoint()
            result
        } else {
            result
        }
    }

    private fun checkQuestion(): Boolean {
        return listQuestion[numberQuestionAndAnswer] in listChoose
    }
    private fun checkCheat(): Boolean {
        return listQuestion[numberQuestionAndAnswer] in listCheat
    }


    private fun checkPoint(boolean: Boolean) {
        if (boolean) {
            point++
        } else {
            if (negativeNum < 2) {
                negativeNum++
            } else {
                negativeNum = 0
                point--
            }
        }
    }

    private fun showPoint() {
         pointShow.value = "your score is: $point"
    }

    private fun addTooQuestionChoose() {
        listChoose.add(listQuestion[numberQuestionAndAnswer])
    }

    fun addTooCheat() {
        listCheat.add(listQuestion[numberQuestionAndAnswer])
        addTooQuestionChoose()
    }
}