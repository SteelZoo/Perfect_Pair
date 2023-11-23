package com.olduo.last_dance.preseatation.main.group

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGroupBinding
import com.olduo.last_dance.preseatation.main.MainViewModel
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GroupFragment : BaseFragment<FragmentGroupBinding>(FragmentGroupBinding::bind,R.layout.fragment_group) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val groupViewModel: GroupViewModel by viewModels()


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
        setObserve()

        getGroupGameList()
    }

    private fun getGroupGameList(){
        groupViewModel.getGameSets(mainViewModel.selectedGroup.id)
    }

    private fun setListener() {
        binding.fabMakeGame.setOnClickListener {
            this.findNavController().navigate(R.id.action_groupFragment_to_crateGameFragment)
        }

        binding.fabInvite.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, mainViewModel.selectedGroup.code)
            startActivity(Intent.createChooser(sharingIntent, "그룹 초대 코드"))
        }
    }

    private fun setObserve(){
        groupViewModel.gameSetList.observe(viewLifecycleOwner){
            binding.rvGameList.adapter = GroupGameListAdapter(it){
                mainViewModel.selectedGame = it.copy()
                findNavController().navigate(R.id.action_groupFragment_to_gameRankFragment)
            }
        }
    }
}