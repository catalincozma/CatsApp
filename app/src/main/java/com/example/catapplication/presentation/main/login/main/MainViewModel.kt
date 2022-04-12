package com.example.catapplication.presentation.main.login.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catapplication.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val showLogin: MutableLiveData<Boolean> = MutableLiveData()


    init {
        userRepository.currentLoggedUser()
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                showLogin.postValue(it.element() == null)
            }

    }


    fun showLogin(): LiveData<Boolean> {
        return showLogin
    }


}