package com.frcoding.contactsmanagerrecap

import android.app.Application
import android.util.Log
import com.frcoding.contactsmanagerrecap.room.UserDatabase
import com.frcoding.contactsmanagerrecap.room.UserDatabase_Impl
import com.frcoding.contactsmanagerrecap.room.UserRepository
import com.frcoding.contactsmanagerrecap.viewmodel.UserViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ContactsApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        (applicationContext as ContactsApplication).kodein
        import(androidXModule(this@ContactsApplication))
        bind() from singleton { UserDatabase(instance()) }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider {
            UserViewModelFactory(instance())
        }
    }


}