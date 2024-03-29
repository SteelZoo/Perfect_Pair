package com.olduo.last_dance.preseatation.main.grouplist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGroupListBinding
import com.olduo.last_dance.preseatation.main.MainActivity
import com.olduo.last_dance.preseatation.main.MainViewModel
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupListFragment : BaseFragment<FragmentGroupListBinding>(
    FragmentGroupListBinding::bind,
    R.layout.fragment_group_list
) {
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

        Log.d("TAG", "onViewCreated: ${requireActivity()}")

        groupListViewModel.getMyGroup()
        setListener()
        setObserve()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }
        })
    }

    private fun setListener() {
        with(binding) {
            btnCreateGroup.setOnClickListener {
                this@GroupListFragment.findNavController()
                    .navigate(R.id.action_groupListFragment_to_createGroupFragment)
            }

            btnJoinGroup.setOnClickListener {
                this@GroupListFragment.findNavController()
                    .navigate(R.id.action_groupListFragment_to_joinGroupFragment)
            }

            tbGroupList.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.item_logout -> {
                        showLogoutDialog()
                    }
                }
                true
            }
        }
    }


    private fun setObserve() {
        groupListViewModel.groupList.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                binding.rvGroupList.adapter = GroupListAdapter(result.getOrDefault(listOf())) {
                    mainViewModel.selectedGroup = it.copy()
                    findNavController().navigate(R.id.action_groupListFragment_to_groupFragment)
                }
            } else {
                showDefaultSnackbar("그룹불러오기에 실패했습니다.")
            }
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage("로그아웃 하시겠습니까?")
            .setNegativeButton("취소") { dialog, _ ->

            }
            .setPositiveButton("확인") { dialog, _ ->
                mainViewModel.logout()
                (requireActivity() as MainActivity).moveToLoginActivity()
            }
            .show()
    }

    private fun showDefaultSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

}