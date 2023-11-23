package com.olduo.last_dance.preseatation.main.gamerank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGameRankBinding
import com.olduo.last_dance.preseatation.main.MainViewModel
import com.olduo.last_dance.preseatation.main.group.GroupGameListAdapter
import com.olduo.last_dance.preseatation.main.group.GroupViewModel
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameRankFragment : BaseFragment<FragmentGameRankBinding>(FragmentGameRankBinding::bind,R.layout.fragment_game_rank) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val gameRankViewModel: GameRankViewModel by viewModels()

    private lateinit var rankListAdapter: RankListAdapter

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

        setUi()
        setListener()
        setObaserve()

        gameRankViewModel.getScores(mainViewModel.selectedGame.id)
    }

    private fun setUi(){
        with(binding){
            val gameSet = mainViewModel.selectedGame
            layoutTitle.tvName.text = gameSet.title
            layoutTitle.tvDescription.text = "${gameSet.questionList.first().first} vs ${gameSet.questionList.first().second}"

            rankListAdapter = RankListAdapter()
            rvRankList.adapter= rankListAdapter
        }
    }

    private fun setListener(){
        binding.btnJoinQuestion.setOnClickListener {
            this.findNavController().navigate(R.id.action_gameRankFragment_to_gameFragment)
        }

        binding.switchMyRank.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                gameRankViewModel.myScoreList.value?.let {list ->
                    rankListAdapter.submitList(list)
                }
            } else {
                gameRankViewModel.scoreList.value?.let {list ->
                    rankListAdapter.submitList(list)
                }
            }
        }
    }

    private fun setObaserve(){
        gameRankViewModel.scoreList.observe(viewLifecycleOwner){
            rankListAdapter.submitList(it)
        }

        gameRankViewModel.failMessage.observe(viewLifecycleOwner){event->
            event.getContentIfNotHandled()?.let {
                showDefaultSnackbar(it)
            }
        }
    }

    private fun showDefaultSnackbar(msg: String){
        Snackbar.make(binding.root,msg, Snackbar.LENGTH_SHORT).show()
    }
}