package com.example.catapplication.presentation.main.login.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.catapplication.data.locale.model.CatCharacteristics
import com.example.catapplication.data.repository.CatsHomeRepository
import com.example.catapplication.extensions.plusAssign
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class CatViewModel  @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val catsHomeRepository: CatsHomeRepository,
) : ViewModel() {
    private val disposables = CompositeDisposable()

    private val _catDetailsState: MutableLiveData<CatCharacteristics> = MutableLiveData()
    val catDetailsState: LiveData<CatCharacteristics> = _catDetailsState

    init {
        disposables += catsHomeRepository.getDetailsForCat(savedStateHandle.get<String>(CatDetailsActivity.NAME_KEY) ?: "")
            .subscribeOn(AndroidSchedulers.mainThread())
            .map { cat ->

                _catDetailsState.postValue(  cat)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }



}