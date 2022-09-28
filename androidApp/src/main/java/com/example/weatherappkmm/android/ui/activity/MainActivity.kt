package com.example.weatherappkmm.android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherappkmm.android.ui.fragment.MainFragment
import com.example.weatherappkmm.android.R
import com.example.weatherappkmm.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragment= MainFragment()
        val fragmentTransaction = supportFragmentManager
            .beginTransaction()
            .addToBackStack(fragment.javaClass.simpleName)
            .replace(R.id.fragment_container, fragment)

        fragmentTransaction.commitAllowingStateLoss()

    }
}