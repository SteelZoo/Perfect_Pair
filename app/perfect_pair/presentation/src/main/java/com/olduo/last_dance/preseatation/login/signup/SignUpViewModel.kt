package com.olduo.last_dance.preseatation.login.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olduo.last_dance.domain.usecase.user.SignUpUsecase
import com.olduo.last_dance.preseatation.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUsecase: SignUpUsecase
) : ViewModel() {
    val id = MutableLiveData<String>("")
    val name = MutableLiveData<String>("")
    val pass = MutableLiveData<String>("")

    private var _dupCheckedId = ""
    val dupCheckedId
        get() =  _dupCheckedId

    private var _isDup = MutableLiveData<Event<Result<Boolean>>>()
    val isDup: LiveData<Event<Result<Boolean>>>
        get() = _isDup

    private var _isSuccessSignUp = MutableLiveData<Event<Boolean>>()
    val isSuccessSignUp: LiveData<Event<Boolean>>
        get() = _isSuccessSignUp

    fun signUp(){
        viewModelScope.launch {
            if (id.value != null && pass.value != null && name.value != null){
                _isSuccessSignUp.postValue(
                    Event(signUpUsecase(id.value!!, pass.value!!, name.value!!))
                )
            }
        }
    }

    fun checkIdDup(){
        viewModelScope.launch {
            id.value?.let {idString->
                val res = signUpUsecase.checkDup(idString)
                val isDup = res.getOrDefault(true)
                if (!isDup){
                    _dupCheckedId = idString
                }
                _isDup.postValue(Event(res))
            }
        }
    }

}