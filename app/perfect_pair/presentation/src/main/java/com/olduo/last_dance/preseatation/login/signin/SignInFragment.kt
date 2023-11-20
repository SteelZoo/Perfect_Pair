package com.olduo.last_dance.preseatation.login.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentSignInBinding
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::bind,R.layout.fragment_sign_in) {
    private val signInViewModel: SignInViewModel by viewModels()

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

        binding.viewmodel = signInViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setListener()
        setObserve()
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
        signInViewModel.loginedUser.observe(viewLifecycleOwner){
            Snackbar.make(binding.root,it.toString(),Snackbar.LENGTH_SHORT).show()
            this.findNavController().navigate(R.id.action_signInFragment_to_main_nav_graph)
        }
    }
}