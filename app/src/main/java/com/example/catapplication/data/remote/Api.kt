package com.example.catapplication.data.remote

import com.example.catapplication.data.remote.model.CatResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface Api  {
    @GET("breeds")
    fun getCatsFromApi() : Observable<List<CatResponse>>
}