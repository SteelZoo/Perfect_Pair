package com.olduo.last_dance.preseatation.main.grouplist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGroupListBinding
import com.olduo.last_dance.preseatation.main.MainViewModel
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupListFragment : BaseFragment<FragmentGroupListBinding>(FragmentGroupListBinding::bind,R.layout.fragment_group_list) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val groupListViewModel: GroupListViewModel by viewModels()

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

        groupListViewModel.getMyGroup()
        setListener()
        setObserve()


    }

    private fun setListener(){
        with(binding){
            btnCreateGroup.setOnClickListener {
                this@GroupListFragment.findNavController().navigate(R.id.action_groupListFragment_to_createGroupFragment)
            }

            btnJoinGroup.setOnClickListener {
                this@GroupListFragment.findNavController().navigate(R.id.action_groupListFragment_to_joinGroupFragment)
            }
        }
    }

    private fun setObserve(){
        groupListViewModel.groupList.observe(viewLifecycleOwner){result->
            if (result.isSuccess){
                binding.rvGroupList.adapter = GroupListAdapter(result.getOrDefault(listOf())){
                    mainViewModel.selectedGroup = it.copy()
                    findNavController().navigate(R.id.action_groupListFragment_to_groupFragment)
                }
            } else {
                showDefaultSnackbar("그룹불러오기에 실패했습니다.")
            }
        }
    }

    private fun showDefaultSnackbar(msg: String){
        Snackbar.make(binding.root,msg, Snackbar.LENGTH_SHORT).show()
    }

}