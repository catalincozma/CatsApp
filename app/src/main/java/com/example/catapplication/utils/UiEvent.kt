package com.example.catapplication.utils

sealed class UiEvent {
    data class FilterClick(val filter : String) : UiEvent()
    data class CatDetailsClick(val id : String) : UiEvent()
}