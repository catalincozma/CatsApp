package com.example.catapplication.data.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catapplication.data.locale.model.CatCharacteristics
import io.reactivex.rxjava3.core.Observable

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherCharacteristics: CatCharacteristics)

    @Query("SELECT * FROM cat")
    fun getCats(): Observable<List<CatCharacteristics>>

    @Query("SELECT * from cat where name = :name LIMIT 1")
    fun findCat(name: String): Observable<CatCharacteristics>
}