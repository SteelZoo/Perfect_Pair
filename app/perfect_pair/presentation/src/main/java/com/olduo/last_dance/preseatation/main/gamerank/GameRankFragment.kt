package com.olduo.last_dance.preseatation.main.gamerank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGameRankBinding
import com.ssafy.template.board.config.BaseFragment

class GameRankFragment : BaseFragment<FragmentGameRankBinding>(FragmentGameRankBinding::bind,R.layout.fragment_game_rank) {

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

        binding.btnJoinQuestion.setOnClickListener {
            this.findNavController().navigate(R.id.action_gameRankFragment_to_gameFragment)
        }
    }
}