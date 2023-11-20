package com.olduo.last_dance.preseatation.login.signup

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentSignUpBinding
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.sign

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::bind,R.layout.fragment_sign_up) {
    private val signUpViewModel:SignUpViewModel by viewModels()

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

        binding.viewmodel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setListener()
        setObserve()

    }

    private fun setListener(){
        with(binding){
            etSignupId.setEndIconOnClickListener {
                signUpViewModel.id.value?.let {
                    if (it.length < 5){
                        showDefaultSnackbar("아이디가 최소 글자수보다 작습니다")
                    } else {
                        signUpViewModel.checkIdDup()
                    }
                }
            }

            btnSignup.setOnClickListener {
                if (validateInput()){
                    signUpViewModel.signUp()
                }
            }
        }
    }

    private fun setObserve(){
        signUpViewModel.id.observe(viewLifecycleOwner){
            changeDupIconChange(it)
        }

        signUpViewModel.isDup.observe(viewLifecycleOwner){event->
            event.getContentIfNotHandled()?.let {
                if (it.isSuccess){
                    val isDup = it.getOrDefault(true)
                    if (!isDup){
                        showDefaultSnackbar("사용가능한 아이디입니다.")
                    } else {
                        showDefaultSnackbar("이미 존재하는 아이디입니다.")
                    }
                } else if (it.isFailure){
                    showDefaultSnackbar("네트워크 오류")
                }
                // 중복체크 아이콘 변환
                signUpViewModel.id.value?.let {
                    changeDupIconChange(it)
                }
            }
        }

        signUpViewModel.isSuccessSignUp.observe(viewLifecycleOwner){event->
            event.getContentIfNotHandled()?.let {
                if (it){
                    showDefaultSnackbar("회원가입 성공")
                    this.findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                } else{
                    showDefaultSnackbar("회원가입 실패")
                }
            }
        }
    }

    private fun validateInput(): Boolean{
        val id = signUpViewModel.id.value
        val pass = signUpViewModel.pass.value
        val name = signUpViewModel.name.value
        val checkId = signUpViewModel.dupCheckedId

        if (id != null && pass != null && name != null){
            if (id.length < 5) {
                showDefaultSnackbar("아이디가 최소 글자수보다 작습니다")
            } else if (name.length < 3) {
                showDefaultSnackbar("이름이 최소 글자수보다 작습니다")
            } else if (pass.length < 5) {
                showDefaultSnackbar("비밀번호가 최소 글자수보다 작습니다")
            } else if (id != checkId) {
                showDefaultSnackbar("아이디 중복체크를 해야합니다")
            } else {
                return true
            }
        }

        return false
    }

    private fun changeDupIconChange(inputId: String){
        if (inputId == signUpViewModel.dupCheckedId && inputId.length > 5){
            binding.etSignupId.setEndIconTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.bluelagoo_deep)))
        } else {
            binding.etSignupId.setEndIconTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black)))
        }
    }

    private fun showDefaultSnackbar(msg: String){
        Snackbar.make(binding.root,msg, Snackbar.LENGTH_SHORT).show()
    }

}