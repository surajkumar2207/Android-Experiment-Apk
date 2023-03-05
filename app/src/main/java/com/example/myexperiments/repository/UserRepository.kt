package com.example.myexperiments.repository

import androidx.lifecycle.LiveData
import com.example.myexperiments.data.UserDao
import com.example.myexperiments.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun updateUserData(FirstName: String, LastName: String, Age: Int, ID: Int){
        userDao.updateData(FirstName, LastName, Age, ID)
    }

    fun deteThisUser(ID: Int) {
        userDao.deleteThisUser(ID)
    }

    fun deleteAllUser() {
        userDao.deleteAllUser()
    }
}