package com.rensystem.p02_quizapp.domain.model

import com.rensystem.p02_quizapp.data.model.UserDataModel

data class UserModel(
    val id: Int,
    val name: String,
//    val pic:String,
    val pic: Int,
    val score: Int
)

fun UserDataModel.toDomain() = UserModel(id, name, pic, score)
