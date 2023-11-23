package com.olduo.last_dance.preseatation.main.creategame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olduo.last_dance.domain.usecase.quiz.MakeQuizUsecase
import com.olduo.last_dance.preseatation.Event
import com.olduo.last_dance.preseatation.mapper.toQuizDomain
import com.olduo.last_dance.preseatation.model.GameSet
import com.olduo.last_dance.preseatation.model.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel @Inject constructor(
    private val makeQuizUsecase: MakeQuizUsecase
) : ViewModel() {
    val firstString = MutableLiveData<String>()
    val secondString = MutableLiveData<String>()

    private var _addFailure = MutableLiveData<Event<String>>()
    val addFailure: LiveData<Event<String>>
        get() = _addFailure

    private var _isMakeSuccess = MutableLiveData<Result<Boolean>>()
    val isMakeSuccess: LiveData<Result<Boolean>>
        get() = _isMakeSuccess

    val questionList = MutableLiveData<List<Question>>()

    fun makeQuiz(gId: Int, title: String) {
        viewModelScope.launch {
            if (questionList.value != null) {
                makeQuizUsecase(
                    GameSet(
                        0,
                        gId,
                        title,
                        questionList.value!!
                    ).toQuizDomain()
                ).let {res->
                    if (res.isSuccess){
                        _isMakeSuccess.postValue(res)
                    }else{
                        _addFailure.postValue(Event("네트워크 오류"))
                    }
                }
            } else {
                _addFailure.postValue(Event("문제목록이 없습니다."))
            }
        }

    }

    fun addQuestion() {
        with(questionList.value ?: listOf()) {
            if (validateQuestionInput()) {
                questionList.value = toMutableList().apply {
                    add(
                        Question(
                            firstString.value!!, secondString.value!!,
                        )
                    )
                }
                firstString.value = ""
                secondString.value = ""
            }
        }
    }

    fun delete(question: Question) {
        with(questionList.value ?: listOf()) {
            questionList.value = toMutableList().apply { remove(question) }
        }
    }

    private fun validateQuestionInput(): Boolean {
        val first = firstString.value
        val second = secondString.value
        val regexString = "[/^*$#@%]"

        if (first.isNullOrBlank() || second.isNullOrBlank()) {
            _addFailure.value = Event("빈 문장은 추가할 수 없습니다.")
            return false
        } else if (first.contains(Regex(regexString)) || second.contains(Regex(regexString))) {
            _addFailure.value = Event("특수문자 $regexString 들은 추가할 수 없습니다.")
            return false
        }
        return true
    }

}