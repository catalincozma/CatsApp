package com.example.catapplication.data.locale.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_user")
data class UserCharacteristics(
    @PrimaryKey @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String? = null,
)