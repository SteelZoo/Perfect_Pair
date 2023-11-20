package com.olduo.last_dance.preseatation.main.grouplist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGroupListBinding
import com.olduo.last_dance.preseatation.model.Group
import com.ssafy.template.board.config.BaseFragment

class GroupListFragment : BaseFragment<FragmentGroupListBinding>(FragmentGroupListBinding::bind,R.layout.fragment_group_list) {

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

        setListener()

        binding.rvGroupList.adapter = GroupListAdapter(groupList){
            this.findNavController().navigate(R.id.action_groupListFragment_to_groupFragment)
        }
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


    private val groupList = listOf(
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,"id 01",
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        )
    )
}