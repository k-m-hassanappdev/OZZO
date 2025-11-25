package com.example.ozzo.views.register

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.ozzo.base.BaseFragment
import com.example.ozzo.core.DataState
import com.example.ozzo.data.models.UserRegistration
import com.example.ozzo.databinding.FragmentRegisterBinding
import com.example.ozzo.isEmpty
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewModel: RegistrationViewModel by viewModels ()

    override fun setListener() {
        with(binding){
            btnRegister.setOnClickListener {
                etName.isEmpty()
                etEmail.isEmpty()
                etPassword.isEmpty()
                if (!etName.isEmpty() &&!etEmail.isEmpty() && !etPassword.isEmpty()){
                    Toast.makeText(context, "All input done...", Toast.LENGTH_SHORT).show()

                    val user= UserRegistration(
                        etName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        "Seller",
                        ""
                    )

                    viewModel.userRegistration(user)


                }
            }
        }

    }

    //OOAD -> Object Oriented Analysis Design (Code Design)
    override fun allObserver() {
        registrationResponse()
    }

    private fun registrationResponse() {

        viewModel.registrationResponse.observe(viewLifecycleOwner){

            when(it) {
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    loading.show()
                    Toast.makeText(context, "Loading....", Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    loading.dismiss()
                    Toast.makeText(context, "created User : ${it.data}", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }


}