package com.example.catapplication.di

import com.example.catapplication.data.locale.CatDao
import com.example.catapplication.data.locale.UserDao
import com.example.catapplication.data.remote.Api
import com.example.catapplication.data.repository.CatsHomeRepository
import com.example.catapplication.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideCatApi(@Named("retrofit") retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun providesCatsRepository(api: Api, locale: CatDao): CatsHomeRepository {
        return CatsHomeRepository(api,locale)
    }

    @Singleton
    @Provides
    fun providesUserRepository(api: Api, locale: UserDao): UserRepository {
        return UserRepository(locale)
    }

}