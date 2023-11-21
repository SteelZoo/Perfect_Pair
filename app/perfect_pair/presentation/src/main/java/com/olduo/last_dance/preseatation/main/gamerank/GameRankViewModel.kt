package com.olduo.last_dance.preseatation.main.gamerank


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olduo.last_dance.domain.usecase.answer.GetAllUsersAnswerUsecase
import com.olduo.last_dance.preseatation.Event
import com.olduo.last_dance.preseatation.mapper.toPresentation
import com.olduo.last_dance.preseatation.model.MatchScore
import com.olduo.last_dance.preseatation.util.ScoreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameRankViewModel @Inject constructor(
    private val getAllUsersAnswerUsecase: GetAllUsersAnswerUsecase
) : ViewModel() {
    private var _scoreList = MutableLiveData<List<MatchScore>>()
    val scoreList: LiveData<List<MatchScore>>
        get() = _scoreList

    private var _failMessage = MutableLiveData<Event<String>>()
    val failMessage: LiveData<Event<String>>
        get() = _failMessage

    fun getScores(qId: Int){
        viewModelScope.launch {
            getAllUsersAnswerUsecase(qId).getOrNull().let {list ->
                if (list != null){
                    val scoreList = ScoreUtil.getAllMatchingScores(list.map { it.toPresentation() }).sortedByDescending { it.score }
                    _scoreList.postValue(scoreList)
                } else {
                    _failMessage.postValue(Event("순위 호출에 실패했습니다."))
                }
            }
        }
    }
}