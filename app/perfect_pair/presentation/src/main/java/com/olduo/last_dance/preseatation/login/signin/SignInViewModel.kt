package com.olduo.last_dance.preseatation.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olduo.last_dance.domain.usecase.user.SignInUsecase
import com.olduo.last_dance.preseatation.mapper.toPresentation
import com.olduo.last_dance.preseatation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUsecase: SignInUsecase
) : ViewModel() {
    val id = MutableLiveData("")
    val pass = MutableLiveData("")

    private var _loginedUser = MutableLiveData<User>()
    val loginedUser: LiveData<User>
        get() = _loginedUser

    fun signIn(){
        viewModelScope.launch {
            val idString = id.value
            val passString = pass.value
            if (idString != null && passString != null){
                signInUsecase(idString, passString)?.let {
                    _loginedUser.postValue(it.toPresentation())
                }
            }
        }
    }
}