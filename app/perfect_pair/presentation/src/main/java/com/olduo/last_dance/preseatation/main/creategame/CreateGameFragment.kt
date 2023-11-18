package com.olduo.last_dance.preseatation.main.creategame

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentCrateGameBinding
import com.olduo.last_dance.preseatation.dto.Question
import com.ssafy.template.board.config.BaseFragment

class CreateGameFragment : BaseFragment<FragmentCrateGameBinding>(FragmentCrateGameBinding::bind,R.layout.fragment_crate_game) {

    private val createGameListAdapter = CreateGameListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvQuestions.adapter = createGameListAdapter

        setListener()
    }

    private fun setListener(){
        binding.btnAddQuestion.setOnClickListener {
            val list = createGameListAdapter.currentList.toMutableList().apply {
                add(
                    Question(
                        binding.etFirstQuestion.text.toString(),
                        binding.etSecondQuestion.text.toString()
                    )
                )
            }

            Log.d("TAG", "setListener: $list")

            createGameListAdapter.submitList(list)

            binding.etFirstQuestion.text.clear()
            binding.etSecondQuestion.text.clear()

            binding.etFirstQuestion.requestFocus()
        }
    }
}