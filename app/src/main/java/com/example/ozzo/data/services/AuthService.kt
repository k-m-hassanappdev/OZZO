package com.example.ozzo.data.services

import com.example.ozzo.data.models.UserRegistration
import com.example.ozzo.data.models.userLogin
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthService {
    fun userRegistration(user: UserRegistration): Task<AuthResult>
    fun userLogin(userLogin: userLogin):Task<AuthResult>
    fun createUser(user: UserRegistration)
}