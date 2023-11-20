package com.olduo.last_dance.preseatation.main

import androidx.lifecycle.ViewModel
import com.olduo.last_dance.domain.usecase.user.SignInUsecase
import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import com.olduo.last_dance.preseatation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferencesUtil: SharedPreferencesUtil,
    private val signInUsecase: SignInUsecase
) : ViewModel() {
    private var _user: User? = null
    val user: User?
        get() = _user?.copy()
}