package com.example.catapplication.presentation.main.login.cats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapplication.databinding.CatsFragmentBinding
import com.example.catapplication.extensions.plusAssign
import com.example.catapplication.presentation.main.login.details.CatDetailsActivity
import com.example.catapplication.utils.UiEvent
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable


@AndroidEntryPoint
class CatsHomeFragment : Fragment() {

    var binding: CatsFragmentBinding? = null
    private val homeViewModel: CatsHomeViewModel by viewModels()
    private val catsController = CatsHomeController()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CatsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { binding ->
            binding.catsEpoxy.apply {
                setController(controller = catsController)
                layoutManager = LinearLayoutManager(this.context)
            }

            homeViewModel.showCats().observe(viewLifecycleOwner) { cats ->
                catsController.setModels(cats)
            }

            homeViewModel.showLoadingHomeCats.observe(viewLifecycleOwner) { showLoading ->
                binding.loading.visibility = if (showLoading) View.VISIBLE else View.GONE
            }

        }

        disposables += catsController.uiEvents.subscribe {
            when (it) {
                is UiEvent.FilterClick -> {
                    homeViewModel.catsFilter(it.filter)
                }
                is UiEvent.CatDetailsClick -> {
                    activity?.let { activity ->
                        val myIntent = Intent(activity, CatDetailsActivity::class.java)
                        myIntent.putExtra(CatDetailsActivity.NAME_KEY, it.id)
                        activity.startActivity(myIntent)
                    }
                }
            }
        }


    }
}