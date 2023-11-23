package com.olduo.last_dance.preseatation.main

import androidx.lifecycle.ViewModel
import com.olduo.last_dance.domain.usecase.user.SignInUsecase
import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import com.olduo.last_dance.preseatation.model.GameSet
import com.olduo.last_dance.preseatation.model.Group
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferencesUtil: SharedPreferencesUtil,
    private val signInUsecase: SignInUsecase
) : ViewModel() {
    lateinit var selectedGroup: Group
    lateinit var selectedGame: GameSet
    var isStartedApp = false

    fun logout(){
        sharedPreferencesUtil.userId = null
        sharedPreferencesUtil.autoLoginState = false
    }
}