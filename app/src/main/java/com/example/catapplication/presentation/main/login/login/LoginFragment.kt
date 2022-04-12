package com.example.catapplication.presentation.main.login.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.fragment.app.viewModels
import com.example.catapplication.R
import com.example.catapplication.data.DataHandler
import com.example.catapplication.databinding.FragmentLoginBinding
import com.example.catapplication.presentation.main.login.main.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    var binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dataHandlerState.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.Success<*> -> {
                    activity?.onBackPressed()
                }
                is DataHandler.Failure -> {
                    MaterialAlertDialogBuilder(requireContext()).setTitle(
                        "Error"
                    ).setMessage(dataHandler.message)

                        .show()
                }
            }
        }

        binding?.let { bind ->
            bind.login.setOnClickListener {
                viewModel.set(bind.email.text.toString().trim(),bind.password.toString().trim())
            }
        }


    }

}