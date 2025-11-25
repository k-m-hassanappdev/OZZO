package com.example.ozzo.views.dashboard

import androidx.navigation.fragment.findNavController
import com.example.ozzo.R
import com.example.ozzo.base.BaseFragment
import com.example.ozzo.databinding.FragmentDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {
    @Inject
    lateinit var jAuth: FirebaseAuth
    override fun setListener() {
        with(binding){
            btnLogout.setOnClickListener {
                jAuth.signOut()
                findNavController().navigate(R.id.action_dashboardFragment_to_startFragment)
            }
        }
    }

    override fun allObserver() {

    }


}