package com.olduo.last_dance.preseatation.main.joingroup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentJoinGroupBinding
import com.ssafy.template.board.config.BaseFragment

class JoinGroupFragment : BaseFragment<FragmentJoinGroupBinding>(FragmentJoinGroupBinding::bind,R.layout.fragment_join_group) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join_group, container, false)
    }

}