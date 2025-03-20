package com.rensystem.p02_quizapp.domain.usecase

import com.rensystem.p02_quizapp.domain.QuizRepository
import com.rensystem.p02_quizapp.domain.model.UserModel
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: QuizRepository) {
    suspend operator fun invoke() : List<UserModel>{
        val users = repository.getAllUsersFromMock()
        return users
    }
}