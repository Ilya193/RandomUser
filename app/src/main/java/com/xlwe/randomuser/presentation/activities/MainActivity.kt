package com.xlwe.randomuser.presentation.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.xlwe.randomuser.R
import com.xlwe.randomuser.databinding.ActivityMainBinding
import com.xlwe.randomuser.presentation.OnClick
import com.xlwe.randomuser.presentation.fragments.UserFragment
import com.xlwe.randomuser.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClick {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, UserFragment.newInstance())
            .commit()
    }

    override fun clickPhone(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
        else {
            Snackbar.make(binding.root, R.string.no_apps_phone, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun clickLocation(latitude: String, longitude: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude, $longitude"))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
        else {
            Snackbar.make(binding.root, R.string.no_apps_phone, Snackbar.LENGTH_SHORT).show()
        }
    }
}