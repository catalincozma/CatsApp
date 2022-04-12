package com.example.catapplication.presentation.main.login.login

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.catapplication.data.DataHandler
import com.example.catapplication.data.locale.CatDataBase
import com.example.catapplication.data.repository.UserRepository
import com.example.catapplication.getOrAwaitValue
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginViewModelTest : TestCase() {

    private lateinit var loginViewModel: LoginViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()

        val db =
            Room.inMemoryDatabaseBuilder(context, CatDataBase::class.java).allowMainThreadQueries()
                .build()

        val dataSource = UserRepository(db.userDao())

        loginViewModel = LoginViewModel(dataSource)

    }

    @Test
    fun testLoginViewModel() {
        loginViewModel.set("catlin@test.ro", "password")
        val result = loginViewModel.dataHandlerState.getOrAwaitValue()
    }


}