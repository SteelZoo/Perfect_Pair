package com.olduo.last_dance.preseatation.main.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGameBinding
import com.olduo.last_dance.preseatation.dto.GameSet
import com.olduo.last_dance.preseatation.dto.Question
import com.ssafy.template.board.config.BaseFragment

class GameFragment :
    BaseFragment<FragmentGameBinding>(FragmentGameBinding::bind, R.layout.fragment_game) {

    private lateinit var questionListAdapter: QuestionListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionListAdapter = QuestionListAdapter(gameSet()) {
            binding.vpQuestions.currentItem = binding.vpQuestions.currentItem + 1
        }
        binding.vpQuestions.adapter = questionListAdapter
    }

    private fun gameSet(): GameSet {
        return GameSet(
            "1",
            "1",
            listOf(
                Question("문제문제문제문제", "문제문제문제문제"),
                Question("문제문제문제문제", "문제문제문제문제"),
                Question("문제문제문제문제", "문제문제문제문제"),
                Question("문제문제문제문제", "문제문제문제문제")
            )
        )
    }

}