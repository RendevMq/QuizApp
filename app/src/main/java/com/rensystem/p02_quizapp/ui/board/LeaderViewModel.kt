package com.rensystem.p02_quizapp.ui.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rensystem.p02_quizapp.domain.model.UserModel
import com.rensystem.p02_quizapp.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    //Se crea el StateFlow
    private val _userList = MutableStateFlow<List<UserModel>?>(null)
    //Exposicion del State Flow
    val userList : StateFlow<List<UserModel>?> = _userList

    fun onCreateMe(){
        viewModelScope.launch {
            val result = getUsersUseCase()
            _userList.value = result
        }
    }

}