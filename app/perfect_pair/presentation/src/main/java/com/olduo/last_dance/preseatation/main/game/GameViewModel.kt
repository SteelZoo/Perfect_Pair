package com.olduo.last_dance.preseatation.main.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olduo.last_dance.domain.model.AnswerDomain
import com.olduo.last_dance.domain.usecase.answer.GetUserAnswerByIdUsecase
import com.olduo.last_dance.domain.usecase.answer.SendAnswerUsercase
import com.olduo.last_dance.domain.usecase.answer.UpdateAnswerUsecase
import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import com.olduo.last_dance.preseatation.mapper.answerListToRawString
import com.olduo.last_dance.preseatation.mapper.toPresentation
import com.olduo.last_dance.preseatation.model.Answer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val updateAnswerUsecase: UpdateAnswerUsecase,
    private val getUserAnswerByIdUsecase: GetUserAnswerByIdUsecase,
    private val sendAnswerUsercase: SendAnswerUsercase,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {
    private var _isSendSuccess = MutableLiveData<Result<Boolean>>()
    val isSendSuccess: LiveData<Result<Boolean>>
        get() = _isSendSuccess

    private var _isSolved = MutableLiveData<Answer?>()
    val isSolved: LiveData<Answer?>
        get() = _isSolved

    fun sendAnswer(qId: Int, answerList: List<Int>) {
        viewModelScope.launch {
            sharedPreferencesUtil.userId?.let { userId ->
                val answerDomain = AnswerDomain(
                    0, qId, userId, "", answerListToRawString(answerList)
                )

                val res = if (isSolved.value != null){
                    updateAnswerUsecase(answerDomain)
                } else {
                    sendAnswerUsercase(answerDomain)
                }

                _isSendSuccess.postValue(res)
            }
        }
    }

    fun getSolvedAnswer(qId: Int){
        viewModelScope.launch {
            sharedPreferencesUtil.userId?.let {userid->
                _isSolved.postValue(
                    getUserAnswerByIdUsecase(qId,userid).getOrNull()?.toPresentation()
                )
            }
        }
    }


}