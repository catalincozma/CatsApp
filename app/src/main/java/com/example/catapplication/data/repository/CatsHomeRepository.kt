package com.example.catapplication.data.repository

import com.example.catapplication.data.locale.CatDao
import com.example.catapplication.data.locale.model.CatCharacteristics
import com.example.catapplication.data.remote.Api
import com.example.catapplication.data.remote.model.CatResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class CatsHomeRepository @Inject constructor(
    private val api: Api,
    private val catDao: CatDao,

    ) {
    private val catsSubject = BehaviorSubject.create<EventsRepository>()

    fun observableGetCats(): Observable<List<CatCharacteristics>> {
        return catsSubject.filter { it == EventsRepository.LoadCats }.flatMap {
            return@flatMap catDao.getCats().map { it }
        }
    }

    fun getDetailsForCat(id: String): Observable<CatCharacteristics> {
        return catDao.findCat(id).subscribeOn(Schedulers.io())
    }

    fun getCats(): Observable<List<CatResponse>> {
        return api.getCatsFromApi()
            .observeOn(Schedulers.computation())
            .map {
                return@map it
            }.doOnNext {
                it.forEach { cat ->
                    catDao.insert(cat.toCatCharacteristics())
                }
                catsSubject.onNext(EventsRepository.LoadCats)
            }
            .subscribeOn(Schedulers.computation())


    }

    sealed class EventsRepository {
        object LoadCats : EventsRepository()
    }

}