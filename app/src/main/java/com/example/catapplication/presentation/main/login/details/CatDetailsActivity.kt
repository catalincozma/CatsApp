package com.example.catapplication.presentation.main.login.details

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapplication.R
import com.example.catapplication.data.DataHandler
import com.example.catapplication.databinding.ActivityDetailsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.lifecycle.Observer
import com.example.catapplication.presentation.main.login.main.MainViewModel
import com.example.catapplication.utils.loadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatDetailsActivity : AppCompatActivity() {

    lateinit var viewModel: CatViewModel;
    private lateinit var binding: ActivityDetailsBinding

    companion object {
        const val NAME_KEY = "name_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: CatViewModel by viewModels()

        viewModel.catDetailsState.observe(this, Observer { catDetails ->

            binding.image.ratio =
                catDetails.image.height.toDouble() / catDetails.image.width.toDouble()
            binding.image.loadFromUrl(catDetails.image.url)
            binding.name.text = catDetails.name
            binding.countryCode.text = "Country code ${catDetails.countryCode ?: ""} "
            binding.description.text = catDetails.description
            binding.wiki.text = catDetails.wikipediaUrl
            binding.temperament.text = "Temperament ${catDetails.temperament}"

        })

    }


}

