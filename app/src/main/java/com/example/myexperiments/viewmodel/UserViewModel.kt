package com.example.myexperiments.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myexperiments.data.UserDatabase
import com.example.myexperiments.repository.UserRepository
import com.example.myexperiments.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun updateUser(FirstName: String, LastName: String, Age: Int, ID: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUserData(FirstName, LastName, Age, ID)
        }
    }

    fun deleteThisUser(ID: Int) {
        viewModelScope.launch(Dispatchers.IO){
            repository.deteThisUser(ID)
        }

    }

    fun deleteAllUser() {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUser()
        }
    }

}