package com.olduo.last_dance.preseatation.main.grouplist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGroupListBinding
import com.olduo.last_dance.preseatation.dto.Group
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
        binding.rvGroupList.adapter = GroupListAdapter(
            groupList
        )
    }


    private val groupList = listOf(
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        ),
        Group(
            1,
            "구미 6반 마지막 관통",
            "최고의 짝을 정하자!",
            "1"
        )
    )
}