package com.xlwe.randomuser.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xlwe.randomuser.R
import com.xlwe.randomuser.presentation.fragments.UserFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, UserFragment.newInstance())
            .commit()
    }
}