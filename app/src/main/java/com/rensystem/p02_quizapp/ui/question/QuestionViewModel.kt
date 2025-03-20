package com.rensystem.p02_quizapp.ui.question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rensystem.p02_quizapp.domain.model.QuestionModel
import com.rensystem.p02_quizapp.domain.usecase.GetQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {

    private val _questionList = MutableStateFlow<List<QuestionModel>?>(null)
    val questionList: StateFlow<List<QuestionModel>?> = _questionList

    fun onCreateMe() {
        viewModelScope.launch {
            val result = getQuestionsUseCase()
            _questionList.value = result
        }
    }
}