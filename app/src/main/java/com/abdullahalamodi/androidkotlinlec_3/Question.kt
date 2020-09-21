package com.abdullahalamodi.androidkotlinlec_3

import androidx.annotation.StringRes

data class Question(
    @StringRes val textResId: Int,
    val answer: Boolean,
    var isAnswered: Boolean = false
) {
    companion object {
        var grade: Int = 0;
        var answeredQuestions: Int = 0;
        fun checkAnsweredQuestions(allQuestions: Int): Boolean {
            if (allQuestions == answeredQuestions)
                return true;
            return false;
        }
    }
}