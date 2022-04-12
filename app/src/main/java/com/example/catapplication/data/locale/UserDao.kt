package com.example.catapplication.data.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catapplication.data.locale.model.UserCharacteristics
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract    fun insert(userDao: UserCharacteristics)

    @Query("Select * from cat_user LIMIT 1")
    abstract   fun currentUser(): Observable<UserCharacteristics>

    @Query("Select * from cat_user LIMIT 1")
    abstract fun maybeCurrentUser(): Maybe<UserCharacteristics>

}