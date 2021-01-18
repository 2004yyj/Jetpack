package com.example.dgsw.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.dgsw.musicplayer.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.viewModel = viewModel

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.music -> {
                    binding.textView.text = "음악"
                }
                R.id.search -> {
                    binding.textView.text = "검색"
                }
            }
            false
        }
    }


}