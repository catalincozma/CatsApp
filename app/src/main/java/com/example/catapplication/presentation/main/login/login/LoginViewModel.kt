package com.example.catapplication.presentation.main.login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catapplication.data.DataHandler
import com.example.catapplication.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _getDataHandlerState: MutableLiveData<DataHandler> = MutableLiveData()
    val dataHandlerState: LiveData<DataHandler> = _getDataHandlerState



    fun set (email : String,password : String){

        if (!isEmailValid(email)) {
            _getDataHandlerState.value = DataHandler.Failure("Please enter a valid email address")
            return
        }
        if (password.isEmpty()) {
            _getDataHandlerState.value = DataHandler.Failure("Please enter a password")
            return
        }

        userRepository.insertUser(email,password).subscribe({
            _getDataHandlerState.value = DataHandler.Success("Success")
        }, {
            _getDataHandlerState.value = DataHandler.Failure(it.message)
        })
    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
}
