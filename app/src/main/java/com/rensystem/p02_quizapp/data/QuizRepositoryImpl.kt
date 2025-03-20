package com.rensystem.p02_quizapp.data

import com.rensystem.p02_quizapp.data.mock.MockQuestionService
import com.rensystem.p02_quizapp.data.mock.MockUserService
import com.rensystem.p02_quizapp.data.model.QuestionDataModel
import com.rensystem.p02_quizapp.data.model.UserDataModel
import com.rensystem.p02_quizapp.domain.QuizRepository
import com.rensystem.p02_quizapp.domain.model.QuestionModel
import com.rensystem.p02_quizapp.domain.model.UserModel
import com.rensystem.p02_quizapp.domain.model.toDomain
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val mockQuestion: MockQuestionService,
    private val mockUser: MockUserService
) : QuizRepository {
    override suspend fun getAllQuestionsFromMock(): List<QuestionModel> {
        val response: List<QuestionDataModel> = mockQuestion.getQuestionList()
        return response.map { it.toDomain() }
    }

    override suspend fun getAllUsersFromMock(): List<UserModel> {
        val response: List<UserDataModel> = mockUser.getUserList()
        return response.map { it.toDomain() }
    }
}