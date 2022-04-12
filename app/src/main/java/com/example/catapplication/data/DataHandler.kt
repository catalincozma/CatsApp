package com.example.catapplication.data

sealed class DataHandler {
    data class Success<T>(var data: T?) : DataHandler()
    data class Failure(var message: String?) : DataHandler()
}