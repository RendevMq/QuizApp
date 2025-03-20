package com.rensystem.p02_quizapp.data.model

data class QuestionDataModel (
    val id:Int,
    val question:String?,
    val answer_1:String?,
    val answer_2:String?,
    val answer_3:String?,
    val answer_4:String?,
    val correctAnswer:String?,
    val score:Int,
    val picPath:Int?,
    var clickedAnswer:String?
)