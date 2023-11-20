package com.olduo.last_dance.preseatation.main.grouplist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olduo.last_dance.domain.usecase.group.GetUserGroupUsecase
import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import com.olduo.last_dance.preseatation.mapper.toPresentation
import com.olduo.last_dance.preseatation.model.Group
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupListViewModel @Inject constructor(
    private val getUserGroupUsecase: GetUserGroupUsecase,
    private val sharedPreferencesUtil: SharedPreferencesUtil
): ViewModel() {
    private var _groupList = MutableLiveData<Result<List<Group>>>()
    val groupList: LiveData<Result<List<Group>>>
        get() = _groupList

    fun getMyGroup(){
        viewModelScope.launch {
            sharedPreferencesUtil.userId?.let {id->
                val res = getUserGroupUsecase(id).getOrNull()
                if (res != null){
                    _groupList.postValue(Result.success(res.map { it.toPresentation() }))
                } else {
                    _groupList.postValue(Result.failure(Exception()))
                }
            }
        }
    }
}