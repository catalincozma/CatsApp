package com.example.catapplication.presentation.main.login.cats

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.catapplication.FakeApi
import com.example.catapplication.data.locale.CatDataBase
import com.example.catapplication.data.repository.CatsHomeRepository
import com.example.catapplication.data.repository.UserRepository
import com.example.catapplication.getOrAwaitValue
import com.example.catapplication.presentation.main.login.cats.model.CatsHomeUiModel
import com.example.catapplication.presentation.main.login.login.LoginViewModel
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatsHomeViewModelTest : TestCase() {

    private lateinit var catsHomeViewModel: CatsHomeViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()

        val db =
            Room.inMemoryDatabaseBuilder(context, CatDataBase::class.java).allowMainThreadQueries()
                .build()
        val api = FakeApi()

        val dataSource = CatsHomeRepository(api,db.catDao())

        catsHomeViewModel = CatsHomeViewModel(dataSource)


    }


    @Test
    fun testCatsFilter() {
        catsHomeViewModel.catsFilter("Germany")
        val result = catsHomeViewModel.catsHomeUi.getOrAwaitValue()
        assert(result.cats.isEmpty())


    }
}