package com.example.ozzo.views.login

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozzo.R
import com.example.ozzo.base.BaseFragment
import com.example.ozzo.core.DataState
import com.example.ozzo.data.models.UserLogin
import com.example.ozzo.databinding.FragmentLoginBinding
import com.example.ozzo.isEmpty
import com.example.ozzo.views.dashboard.seller.SellerDashboard
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun setListener() {
        with(binding){
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            btnLogin.setOnClickListener {
                etEmail.isEmpty()
                etPassword.isEmpty()
                if (!etEmail.isEmpty() && !etPassword.isEmpty()){
//                    Toast.makeText(context, "All input done...", Toast.LENGTH_LONG).show()
                    val user = UserLogin(etEmail.text.toString(), etPassword.text.toString())
                    viewModel.userLogin(user)
                    loading.show()
                }
            }
        }
    }

    override fun allObserver() {
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
                    Toast.makeText(context, "Login Successful:${it.data}", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(requireContext(), SellerDashboard::class.java))
                    requireActivity().finish()
                }


            }
        }
    }


}
