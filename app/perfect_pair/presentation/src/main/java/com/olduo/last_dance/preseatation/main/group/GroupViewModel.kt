package com.olduo.last_dance.preseatation.main.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olduo.last_dance.domain.usecase.quiz.GetGroupQuizUsecase
import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import com.olduo.last_dance.preseatation.mapper.toGameSet
import com.olduo.last_dance.preseatation.model.GameSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val getGroupQuizUsecase: GetGroupQuizUsecase,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {
    private var _gameSetList = MutableLiveData<List<GameSet>>()
    val gameSetList: LiveData<List<GameSet>>
        get() = _gameSetList

    fun getGameSets(gId: Int){
        viewModelScope.launch {
            getGroupQuizUsecase(gId).getOrNull()?.let {list->
                if (list.isNotEmpty()){
                    _gameSetList.postValue(list.map { it.toGameSet() })
                }
            }
        }
    }
}