package com.olduo.last_dance.preseatation.login.signin

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentSignInBinding
import com.olduo.last_dance.preseatation.login.LoginActivity
import com.olduo.last_dance.preseatation.main.MainViewModel
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::bind,R.layout.fragment_sign_in) {

    private val splashCoroutineScope = CoroutineScope(Dispatchers.Default)
    private val signInViewModel: SignInViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

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
        if (!mainViewModel.isStartedApp){
            splashImage()
            mainViewModel.isStartedApp = true
        } else {
            binding.layoutSplash.visibility = View.GONE
        }

        binding.viewmodel = signInViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setListener()
        setObserve()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        splashCoroutineScope.cancel()
    }

    private fun setListener(){
        binding.tvSigninSignup.setOnClickListener {
            this.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.btnSigninLogin.setOnClickListener {
            signInViewModel.signIn()
        }
    }

    private fun setObserve(){
        signInViewModel.isLoginSuccess.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let {
                if (it){
                    val user = signInViewModel.loginedUser.value!!
                    showDefaultSnackbar("${user.name}님 환영합니다")
                    (requireActivity() as LoginActivity).moveToMainActivity()
                } else {
                    showCustomToast("로그인에 실패했습니다")
                }
            }
        }
    }

    private fun showDefaultSnackbar(msg: String){
        Snackbar.make(binding.root,msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun splashImage(){
        splashCoroutineScope.launch {
            delay(1500)
            withContext(Dispatchers.Main) {
                binding.layoutSplash.visibility = View.GONE
            }
        }
    }
}