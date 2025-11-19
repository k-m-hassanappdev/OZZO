package com.example.ozzo.views.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozzo.R
import com.example.ozzo.base.BaseFragment
import com.example.ozzo.core.DataState
import com.example.ozzo.data.models.userLogin
import com.example.ozzo.databinding.FragmentLoginBinding
import com.example.ozzo.isEmpty
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun setListener() {

        with(binding){
            btnLogin.setOnClickListener {
                etEmail.isEmpty()
                etPassword.isEmpty()
                if (!etEmail.isEmpty() && !etPassword.isEmpty()){
                    Toast.makeText(context, "All input done...", Toast.LENGTH_LONG).show()

                    val user = userLogin(
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )

                    viewModel.userLogin(user)


                }
            }
        }
    }

    override fun allObserver() {
        loginResponse()
    }

    private fun loginResponse() {

        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Error<*> -> {
                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is DataState.Loading<*> -> {
                    loading.show()
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }

                is DataState.Success<*> -> {
                    loading.dismiss()
                    Toast.makeText(context, "Login Successful:${it.data}", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                }


            }
        }
    }


}