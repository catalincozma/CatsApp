package com.example.catapplication

import com.example.catapplication.data.remote.Api
import com.example.catapplication.data.remote.model.CatResponse
import io.reactivex.rxjava3.core.Observable

class FakeApi : Api {

    private val listOfCatResponse = listOf(
        CatResponse(
            name = "Abyssinian",
            origin = "Egypt"
        ),
        CatResponse(
            name = "All",
            origin = "Greece"
        ),

        CatResponse(
            name = "American Bobtail",
            origin = "United States"
        ),
        CatResponse(
            name = "American Shorthair",
            origin = "United States"
        ),
        CatResponse(
            name = "American Wirehair",
            origin = "United States"
        ),
        CatResponse(
            name = "Arabian Mau",
            origin = "United Arab Emirates"
        ),
    )


    override fun getCatsFromApi(): Observable<List<CatResponse>> {
        return Observable.just(listOfCatResponse)
    }

}