package com.rensystem.p02_quizapp.di

import com.rensystem.p02_quizapp.data.QuizRepositoryImpl
import com.rensystem.p02_quizapp.data.mock.MockQuestionService
import com.rensystem.p02_quizapp.data.mock.MockUserService
import com.rensystem.p02_quizapp.domain.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object injectionModule {

    @Singleton
    @Provides
    fun provideRepository(
        mockQuestion: MockQuestionService,
        mockUser: MockUserService
    ) : QuizRepository {
        return QuizRepositoryImpl(mockQuestion,mockUser)
    }
}