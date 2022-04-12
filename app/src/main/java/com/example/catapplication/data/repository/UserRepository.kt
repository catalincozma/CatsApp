package com.example.catapplication.data.repository

import android.util.Log
import com.example.catapplication.utils.Optional
import com.example.catapplication.data.locale.UserDao
import com.example.catapplication.data.locale.model.UserCharacteristics
import com.example.catapplication.extensions.plusAssign

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Named

class UserRepository @Inject constructor(
    private val userDao: UserDao

) {
    private val disposeBag = CompositeDisposable()


    private val userSubject = BehaviorSubject.create<Optional<UserCharacteristics>>()
    fun currentLoggedUser(): Flowable<Optional<UserCharacteristics>> = userSubject.toFlowable(
        BackpressureStrategy.DROP
    )


    init {
        disposeBag += userDao.maybeCurrentUser()
            .isEmpty
            .filter { it }
            .map { Optional.create(null) }
            .subscribeOn(Schedulers.single())
            .onErrorComplete()
            .subscribe { userSubject.onNext(it) }

        disposeBag += userDao.currentUser()
            .map { Optional.create(it) }
            .subscribeOn(Schedulers.single())
            .subscribe { userSubject.onNext(it) }

    }


    fun insertUser(email: String, password: String): Completable {
        return Completable.fromAction {
            userDao.insert(UserCharacteristics(email, password))
        }.doOnComplete {
            userSubject.onNext(Optional.create(UserCharacteristics(email, password)))
        }.subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())


    }

    sealed class EventsRepository {
        object LoadUser : EventsRepository()
    }


}