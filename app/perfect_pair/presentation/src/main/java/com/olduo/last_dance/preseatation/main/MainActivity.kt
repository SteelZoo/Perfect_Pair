package com.olduo.last_dance.preseatation.main

import android.content.Intent
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint


/**
 * TODO
 * GroupFragment ui
 * GameRankFragment ui
 * GameFragment
 * CrateGroupFragment ui
 * JoinGroupFragment ui
 * CrateGameFragment ui
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun moveToLoginActivity(){
        startActivity(Intent(baseContext,LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }

//    override fun onBackPressed() {
//        if(findNavController(R.id.layout_fragment_main).currentDestination?.id == R.id.groupListFragment){
//
//        } else {
//            super.onBackPressed()
//        }
//    }

}