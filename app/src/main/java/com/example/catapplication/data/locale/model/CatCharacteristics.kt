package com.example.catapplication.data.locale.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "cat")
data class CatCharacteristics(
    @PrimaryKey @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "adaptability") val adaptability: Int? = null,
    @ColumnInfo(name = "affection_level") val affectionLevel: Int? = null,
    @ColumnInfo(name = "alt_names") val altNames: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "wikipedia_url") val wikipediaUrl: String? = null,
    @ColumnInfo(name = "origin") val origin: String? = null,
    @ColumnInfo(name = "temperament") val temperament: String? = null,
    @ColumnInfo(name = "country_code") val countryCode: String? = null,
    @ColumnInfo(name = "image") val image: CatImage
) {

    data class CatImage(
        @ColumnInfo(name = "height") val height: Int = 0,
        @ColumnInfo(name = "id") val id: String = "",
        @ColumnInfo(name = "url") val url: String = "",
        @ColumnInfo(name = "width") val width: Int = 0
    )
}
