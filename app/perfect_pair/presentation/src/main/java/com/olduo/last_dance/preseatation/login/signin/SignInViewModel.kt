package com.olduo.last_dance.preseatation.login.signin

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
class SignInViewModel @Inject constructor(
    private val signInUsecase: SignInUsecase,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {
    val id = MutableLiveData("")
    val pass = MutableLiveData("")

    private var _loginedUser = MutableLiveData<User>()
    val loginedUser: LiveData<User>
        get() = _loginedUser

    private var _isLoginSuccess = MutableLiveData<Event<Boolean>>()
    val isLoginSuccess: LiveData<Event<Boolean>>
        get() = _isLoginSuccess

    fun signIn(){
        viewModelScope.launch {
            val idString = id.value
            val passString = pass.value
            if (idString != null && passString != null){
                val user = signInUsecase(idString,passString)?.toPresentation()
                setLoginState(user)
            }
        }
    }

    fun autoLogin() {
        viewModelScope.launch {
            val id = sharedPreferencesUtil.userId
            if (id != null) {
                val user = signInUsecase.autoLotin(id)?.toPresentation()
                setLoginState(user)
            } else {
                _isLoginSuccess.postValue(Event(false))
            }
        }
    }

    private fun setLoginState(user: User?){
        if (user != null){
            sharedPreferencesUtil.userId = user.id
            _loginedUser.postValue(user!!)
            _isLoginSuccess.postValue(Event(true))
        } else {
            _isLoginSuccess.postValue(Event(false))
        }
    }
}