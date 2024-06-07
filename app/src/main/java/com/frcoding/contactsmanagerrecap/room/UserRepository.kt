package com.frcoding.contactsmanagerrecap.room

import androidx.lifecycle.LiveData

class UserRepository(
    private val db: UserDatabase
) {

    val users = db.getUserDao().getAllUsers()

    suspend fun insert(user: User){
        db.getUserDao().insert(user)
    }

    suspend fun update(user: User){
        db.getUserDao().update(user)
    }

    suspend fun delete(user: User){
        db.getUserDao().delete(user)
    }

    suspend fun deleteAll(){
        db.getUserDao().deleteAll()
    }


}