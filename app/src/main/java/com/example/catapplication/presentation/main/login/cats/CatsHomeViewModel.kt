package com.example.catapplication.presentation.main.login.cats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catapplication.data.DataHandler
import com.example.catapplication.data.locale.model.CatCharacteristics
import com.example.catapplication.data.repository.CatsHomeRepository
import com.example.catapplication.presentation.main.login.cats.model.CatsHomeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class CatsHomeViewModel @Inject constructor(
    private val catsHomeRepository: CatsHomeRepository,
) : ViewModel() {

     var catsHomeUi: MutableLiveData<CatsHomeUiModel> =
        MutableLiveData()
    private val listOfTagCats =
        mutableListOf<Pair<CatsHomeUiModel.HomeOriginHeader, List<CatCharacteristics>>>()

    private val _showLoading: MutableLiveData<Boolean> = MutableLiveData()
    val showLoadingHomeCats: LiveData<Boolean> = _showLoading


    init {
        _showLoading.value = true
        catsHomeRepository.getCats()
            .doOnError {
                _showLoading.value = false
            }
            .subscribeOn(Schedulers.computation()).subscribe()

        catsHomeRepository.observableGetCats()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                it.sortedBy { it.name }
                val t = it.groupBy { it.origin }
                    .map { Pair(CatsHomeUiModel.HomeOriginHeader(it.key ?: "", false), it.value) }
                    .toMutableList()
                t.add(0, Pair(CatsHomeUiModel.HomeOriginHeader("All", true), it))

                listOfTagCats.addAll(t)
                _showLoading.value = false
                catsHomeUi.postValue(
                    CatsHomeUiModel(
                        originHeader = t.map { it.first },
                        cats = it
                    )
                )

            }
            .subscribe()


    }


    fun showCats(): LiveData<CatsHomeUiModel> {
        return catsHomeUi
    }

    fun catsFilter(filter: String) {
        val newList = listOfTagCats.filter { it.first.headerTitle == filter }.flatMap { it.second }
        val headerList = listOfTagCats
            .map {
                CatsHomeUiModel.HomeOriginHeader(
                    it.first.headerTitle,
                    it.first.headerTitle == filter
                )
            }
        catsHomeUi.postValue(CatsHomeUiModel(headerList, newList))

    }
}
