package com.olduo.last_dance.preseatation.main.joingroup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olduo.last_dance.domain.usecase.group.JoinGroupUsecase
import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinGroupViewModel @Inject constructor(
    private val joinGroupUsecase: JoinGroupUsecase,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {
    val code = MutableLiveData<String>()

    private var _isJoinSuccess = MutableLiveData<Result<Boolean>>()
    val isJoinSuccess: LiveData<Result<Boolean>>
        get() = _isJoinSuccess

    fun joinToGroup(){
        viewModelScope.launch {
            val id = sharedPreferencesUtil.userId
            val codeString = code.value
            if (!id.isNullOrEmpty() && !codeString.isNullOrEmpty()){
                val result  = joinGroupUsecase(codeString,id)
                _isJoinSuccess.postValue(result)
            }
        }
    }
}