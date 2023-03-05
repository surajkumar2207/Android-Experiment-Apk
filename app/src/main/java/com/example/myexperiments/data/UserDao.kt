package com.example.myexperiments.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myexperiments.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("UPDATE user_table SET firstName =:FirstName, lastName =:LastName, age =:Age  WHERE id =:ID")
    fun updateData(FirstName: String, LastName: String, Age: Int, ID: Int)

    @Query("DELETE FROM user_table where id=:id")
    fun deleteThisUser(id: Int)

    @Query("DELETE FROM user_table")
    fun deleteAllUser()

}