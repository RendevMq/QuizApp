package com.rensystem.p02_quizapp.domain

import com.rensystem.p02_quizapp.domain.model.QuestionModel
import com.rensystem.p02_quizapp.domain.model.UserModel

interface QuizRepository {
    suspend fun getAllQuestionsFromMock(): List<QuestionModel>
    suspend fun getAllUsersFromMock(): List<UserModel>
}