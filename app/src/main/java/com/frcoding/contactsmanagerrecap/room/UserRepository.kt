package com.frcoding.contactsmanagerrecap.room

import androidx.lifecycle.LiveData

class UserRepository(
    private val userDAO: UserDAO
) {

    val users = userDAO.getAllUsers()

    suspend fun insert(user: User){
        userDAO.insert(user)
    }

    suspend fun update(user: User){
        userDAO.update(user)
    }

    suspend fun delete(user: User){
        userDAO.delete(user)
    }

    suspend fun deleteAll(){
        userDAO.deleteAll()
    }


}