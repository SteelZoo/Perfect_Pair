package com.olduo.last_dance.preseatation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olduo.last_dance.domain.usecase.user.SignInUsecase
import com.olduo.last_dance.preseatation.Event
import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import com.olduo.last_dance.preseatation.mapper.toPresentation
import com.olduo.last_dance.preseatation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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