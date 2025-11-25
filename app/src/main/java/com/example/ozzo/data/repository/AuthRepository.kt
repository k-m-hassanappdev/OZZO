package com.example.ozzo.data.repository

import com.example.ozzo.core.Nodes
import com.example.ozzo.data.models.UserRegistration
import com.example.ozzo.data.models.UserLogin
import com.example.ozzo.data.services.AuthService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val jAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthService {
    override fun userRegistration(user: UserRegistration): Task<AuthResult> {


        return jAuth.createUserWithEmailAndPassword(user.email, user.password)

    }

    override fun userLogin(userLogin: UserLogin): Task<AuthResult> {

        return jAuth.signInWithEmailAndPassword(userLogin.email, userLogin.password)

    }


    override fun createUser(user: UserRegistration): Task<Void> {

       return db.collection(Nodes.USER).document(user.userID).set(user)
    }

}