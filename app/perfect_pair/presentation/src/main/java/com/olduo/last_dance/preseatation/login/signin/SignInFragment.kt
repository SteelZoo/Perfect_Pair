package com.olduo.last_dance.preseatation.login.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentSignInBinding
import com.ssafy.template.board.config.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::bind,R.layout.fragment_sign_in) {
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
        binding.tvSigninSignup.setOnClickListener {
            this.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }
}