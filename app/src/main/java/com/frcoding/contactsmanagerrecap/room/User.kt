package com.frcoding.contactsmanagerrecap.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private val id: Int,

    @ColumnInfo(name = "user_name")
    private val name: String,

    @ColumnInfo(name = "user_email")
    private val email: String
) {
}