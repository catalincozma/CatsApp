package com.example.catapplication.data.remote.model

import androidx.room.ColumnInfo
import com.example.catapplication.data.locale.model.CatCharacteristics
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class CatResponse(
    @Json(name = "adaptability") val adaptability: Int? = null,
    @Json(name = "affection_level") val affectionLevel: Int? = null,
    @Json(name = "wikipedia_url") val wikipediaUrl: String = "",
    @Json(name = "origin") val origin: String? = null,
    @Json(name = "temperament") val temperament: String? = null,
    @Json(name = "country_code") val countryCode: String? = null,
    @Json(name = "alt_names") val altNames: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "image") val image: CatImage? = null
) {


    data class CatImage(
        @Json(name = "height") val height: Int = 0,
        @Json(name = "id") val id: String = "",
        @Json(name = "url") val url: String = "",
        @Json(name = "width") val width: Int = 0
    )

    fun toCatCharacteristics(): CatCharacteristics {
        return CatCharacteristics(
            name = this.name ?: "",
            adaptability = this.adaptability,
            affectionLevel = this.affectionLevel,
            altNames = this.altNames,
            description = this.description,
            wikipediaUrl = this.wikipediaUrl,
            origin = this.origin,
            countryCode = this.countryCode,
            temperament = this.temperament,
            image = CatCharacteristics.CatImage(
                height = this.image?.height ?: 0,
                id = this.image?.id ?: "",
                url = this.image?.url ?: "",
                width = this.image?.width ?: 0
            )
        )
    }

}
