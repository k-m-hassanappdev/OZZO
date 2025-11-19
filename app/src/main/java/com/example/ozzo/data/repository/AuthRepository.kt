package com.example.ozzo.data.repository

import com.example.ozzo.data.models.UserRegistration
import com.example.ozzo.data.models.userLogin
import com.example.ozzo.data.services.AuthService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val jAuth: FirebaseAuth
) : AuthService {
    override fun userRegistration(user: UserRegistration): Task<AuthResult> {


        return jAuth.createUserWithEmailAndPassword(user.email, user.password)

    }

    override fun userLogin(userLogin: userLogin): Task<AuthResult> {

        val kAuth= FirebaseAuth.getInstance()

        return kAuth.signInWithEmailAndPassword(userLogin.email, userLogin.password)
    }


    override fun createUser(user: UserRegistration) {

    }

}