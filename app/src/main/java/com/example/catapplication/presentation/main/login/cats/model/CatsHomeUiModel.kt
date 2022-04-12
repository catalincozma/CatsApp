package com.example.catapplication.presentation.main.login.cats.model

import com.example.catapplication.data.locale.model.CatCharacteristics

data class CatsHomeUiModel(
    val originHeader: List<HomeOriginHeader>,
    val cats: List<CatCharacteristics>
) {

    data class HomeOriginHeader(
        val headerTitle: String,
        val isSelected: Boolean,
    )
}