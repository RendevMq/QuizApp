package com.rensystem.p02_quizapp.domain.usecase

import com.rensystem.p02_quizapp.domain.QuizRepository
import com.rensystem.p02_quizapp.domain.model.QuestionModel
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    suspend operator fun invoke():List<QuestionModel> {
        val question = repository.getAllQuestionsFromMock()
        return question
    }
}