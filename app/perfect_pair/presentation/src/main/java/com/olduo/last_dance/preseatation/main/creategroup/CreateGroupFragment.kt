package com.olduo.last_dance.preseatation.main.creategroup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentCreateGroupBinding
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateGroupFragment : BaseFragment<FragmentCreateGroupBinding>(FragmentCreateGroupBinding::bind,R.layout.fragment_create_group) {

    private val createGroupViewModel: CreateGroupViewModel by viewModels()

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

        binding.viewmodel = createGroupViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setListener()
        setObserve()
    }

    private fun setListener(){
        binding.btnCreateGroup.setOnClickListener {
            val title = createGroupViewModel.title.value
            val des = createGroupViewModel.des.value

            if (checkInputValidate(title, des)){
                showLoadingDialog()
                createGroupViewModel.createGroup(title!!,des!!)
            } else {
                showDefaultSnackbar("방 제목과 설명을 바르게 입력하세요.")
            }
        }
    }

    private fun setObserve(){
        createGroupViewModel.isSuccess.observe(viewLifecycleOwner){result ->
            dismissLoadingDialog()
            if (result.isSuccess && result.getOrDefault(false)){
                showDefaultSnackbar("그룹생성에 성공했습니다")
                findNavController().popBackStack()
            } else {
                showDefaultSnackbar("그룹생성에 실패했습니다")
            }
        }
    }

    private fun checkInputValidate(title: String?, des: String?): Boolean{
        Log.d("TAG", "checkInputValidate: $title $des")
        return !title.isNullOrBlank() && !des.isNullOrBlank() && 2 <= title.length && title.length <= 15 && des.length <= 30
    }

    private fun showDefaultSnackbar(msg: String){
        Snackbar.make(binding.root,msg, Snackbar.LENGTH_SHORT).show()
    }
}