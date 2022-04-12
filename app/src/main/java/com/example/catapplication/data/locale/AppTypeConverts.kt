package com.example.catapplication.data.locale

import androidx.room.TypeConverter
import com.example.catapplication.data.locale.model.CatCharacteristics
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.*

class AppTypeConverts {

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()


    @TypeConverter
    fun stringToCampaignDetails(value: String?): CatCharacteristics.CatImage? {
        value?.let {
            return moshi.adapter(CatCharacteristics.CatImage::class.java).fromJson(it)
        }
        return null
    }

    @TypeConverter
    fun campaignDetailsToString(value: CatCharacteristics.CatImage?): String? {
        value?.let {
            return moshi.adapter(CatCharacteristics.CatImage::class.java).toJson(it)
        }
        return null
    }

}