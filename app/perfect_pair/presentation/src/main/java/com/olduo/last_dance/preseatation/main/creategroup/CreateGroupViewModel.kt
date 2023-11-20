package com.olduo.last_dance.preseatation.main.creategroup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olduo.last_dance.domain.model.GroupDomain
import com.olduo.last_dance.domain.usecase.group.CreateGroupUsecase
import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGroupViewModel @Inject constructor(
    private val createGroupUsecase: CreateGroupUsecase,
    private val sharedPreferencesUtil: SharedPreferencesUtil
): ViewModel() {
    val title = MutableLiveData<String>()
    val des = MutableLiveData<String>()

    private var _isSuccess = MutableLiveData<Result<Boolean>>()
    val isSuccess: LiveData<Result<Boolean>>
        get() = _isSuccess

    fun createGroup(title: String, description: String){
        viewModelScope.launch {
            sharedPreferencesUtil.userId?.let {id ->
                val res = createGroupUsecase(GroupDomain(title,description,id))
                _isSuccess.postValue(res)
            }
        }
    }
}