package com.olduo.last_dance.preseatation.main.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGroupBinding
import com.olduo.last_dance.preseatation.dto.GameSet
import com.olduo.last_dance.preseatation.dto.Question
import com.ssafy.template.board.config.BaseFragment

class GroupFragment : BaseFragment<FragmentGroupBinding>(FragmentGroupBinding::bind,R.layout.fragment_group) {

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

        setListener()

        binding.rvGameList.adapter = GroupGameListAdapter(gamelist()){
            this.findNavController().navigate(R.id.action_groupFragment_to_gameRankFragment)
        }
    }

    private fun setListener(){
        binding.fabMakeGame.setOnClickListener {
            this.findNavController().navigate(R.id.action_groupFragment_to_crateGameFragment)
        }
    }

    private fun gamelist():List<GameSet> {
        return mutableListOf<GameSet>().apply {
           for (i in 1..10){
               this.add(
                   GameSet(
                       i.toString(),
                       i.toString(),
                       listOf(
                           Question("", "")
                       )
                   )
               )
           }
        }
    }
}