package com.olduo.last_dance.preseatation.main.joingroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentJoinGroupBinding
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinGroupFragment : BaseFragment<FragmentJoinGroupBinding>(
    FragmentJoinGroupBinding::bind,
    R.layout.fragment_join_group
) {

    private val joinGroupViewModel: JoinGroupViewModel by viewModels()

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

        binding.viewmodel = joinGroupViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setListener()
        setObserve()
    }

    private fun setListener() {
        binding.btnJoinGroup.setOnClickListener {
            if (checkInputValidate()) {
                showLoadingDialog()
                joinGroupViewModel.joinToGroup()
            }
        }
    }

    private fun setObserve() {
        joinGroupViewModel.isJoinSuccess.observe(viewLifecycleOwner) { result ->
            dismissLoadingDialog()
            if (result.isSuccess && result.getOrDefault(false)){
                showDefaultSnackbar("성공적으로 참여했습니다!")
                findNavController().popBackStack()
            } else {
                showDefaultSnackbar("그룹에 참여하지 못했습니다\n그룹 코드를 확인해주세요.")
            }
        }
    }

    private fun checkInputValidate(): Boolean {
        return !joinGroupViewModel.code.value.isNullOrBlank()
    }

    private fun showDefaultSnackbar(msg: String){
        Snackbar.make(binding.root,msg, Snackbar.LENGTH_SHORT).show()
    }
}