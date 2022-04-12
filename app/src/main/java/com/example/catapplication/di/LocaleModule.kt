package com.example.catapplication.di

import android.content.Context
import androidx.room.Room
import com.example.catapplication.data.locale.CatDao
import com.example.catapplication.data.locale.CatDataBase
import com.example.catapplication.data.locale.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocaleModule {

    @Provides
    @Singleton
    fun provideCatAppDatabase(@ApplicationContext appContext: Context): CatDataBase {
        return Room.databaseBuilder(
            appContext,
            CatDataBase::class.java,
            "cat_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCatChannelDao(catDataBase: CatDataBase): CatDao {
        return catDataBase.catDao()
    }

    @Provides
    fun provideUserChannelDao(catDataBase: CatDataBase): UserDao {
        return catDataBase.userDao()
    }

}