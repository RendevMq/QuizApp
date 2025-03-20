package com.rensystem.p02_quizapp.data.mock

import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.data.model.UserDataModel
import com.rensystem.p02_quizapp.domain.model.UserModel
import javax.inject.Inject

class MockUserService @Inject constructor(){
    suspend fun getUserList(): List<UserDataModel> {
        return mutableListOf(
            UserDataModel(id = 1, name = "Sophia", pic = R.drawable.person1, score = 4850),
            UserDataModel(id = 2, name = "Daniel", pic = R.drawable.person2, score = 4560),
            UserDataModel(id = 3, name = "James", pic = R.drawable.person3, score = 3873),
            UserDataModel(id = 4, name = "John Smith", pic = R.drawable.person4, score = 3250),
            UserDataModel(id = 5, name = "Emily Johnson", pic = R.drawable.person5, score = 3015),
            UserDataModel(id = 6, name = "David Brown", pic = R.drawable.person6, score = 2970),
            UserDataModel(id = 7, name = "Sarah Wilson", pic = R.drawable.person7, score = 2870),
            UserDataModel(id = 8, name = "Michael Davis", pic = R.drawable.person8, score = 2670),
            UserDataModel(id = 9, name = "Sarah Wilson", pic = R.drawable.person9, score = 2380),
            UserDataModel(id = 10, name = "Sarah Wilson", pic = R.drawable.person9, score = 2380)
        )
    }
}