package com.example.catapplication.presentation.main.login.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import com.example.catapplication.R
import com.example.catapplication.databinding.ActivityMainBinding
import com.example.catapplication.presentation.main.login.cats.CatsHomeFragment
import com.example.catapplication.presentation.main.login.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.showLogin().observe(this,Observer{showLogin ->
            if (showLogin){
                goToFragment<LoginFragment>(true)

            }else{
                goToFragment<CatsHomeFragment>()
            }

        })

    }

    private inline fun <reified F : Fragment> goToFragment(
        toBackStack: Boolean = false,
        bundle: Bundle? = null
    ) =
        supportFragmentManager.commit {
            if (toBackStack) addToBackStack(F::class.java.name)
            replace<F>(R.id.content, args = bundle)
        }
}