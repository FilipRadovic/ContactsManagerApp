package com.frcoding.contactsmanagerrecap.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frcoding.contactsmanagerrecap.room.User
import com.frcoding.contactsmanagerrecap.room.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
): ViewModel(), Observable {

    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete: User

    @Bindable
    val inputName = MutableLiveData<String?>()
    @Bindable
    val inputEmail = MutableLiveData<String?>()
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()
    @Bindable
    val deleteButtonText = MutableLiveData<String>()
    @Bindable
    val clearAllButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        deleteButtonText.value = "Delete"
        clearAllButtonText.value = "Clear All"
    }

    fun saveOrUpdate(){
        //Update
        if (isUpdateOrDelete){
            userToUpdateOrDelete.name = inputName.value!!
            userToUpdateOrDelete.email = inputEmail.value!!
            update(userToUpdateOrDelete)
        }
        else{
            //Insert
            val name = inputName.value!!
            val email = inputEmail.value!!

            insert(User(0, name, email))

            inputName.value = null
            inputEmail.value = null
        }
    }

    fun onDelete(){
        if (isUpdateOrDelete){
            delete(userToUpdateOrDelete)
        }
    }

    fun onClearAll(){
        deleteAll()
    }

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun update(user: User) = viewModelScope.launch {
        repository.update(user)

        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)

        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
    }

    fun initUpdateAndDelete(selectedItem: User) {
        inputName.value = selectedItem.name
        inputEmail.value = selectedItem.email
        isUpdateOrDelete = true
        userToUpdateOrDelete = selectedItem
        saveOrUpdateButtonText.value = "Update"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}