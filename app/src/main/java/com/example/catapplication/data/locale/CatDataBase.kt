package com.example.catapplication.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.catapplication.data.locale.model.CatCharacteristics
import com.example.catapplication.data.locale.model.UserCharacteristics

@Database(entities = [
    (CatCharacteristics::class),
    (UserCharacteristics::class)], version = 1, exportSchema = false)

@TypeConverters(AppTypeConverts::class)
abstract class CatDataBase : RoomDatabase() {
    abstract fun catDao(): CatDao
    abstract fun userDao() : UserDao
}