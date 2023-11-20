package com.olduo.last_dance.preseatation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olduo.last_dance.preseatation.R
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
}