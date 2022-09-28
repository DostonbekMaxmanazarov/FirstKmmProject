package com.example.weatherappkmm.android.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherappkmm.android.ui.fragment.CurrencyFragment
import com.example.weatherappkmm.android.ui.fragment.NewsFragment
import com.example.weatherappkmm.android.ui.fragment.WeatherFragment

class MainPageAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {

            0 -> {
                val fm = WeatherFragment()
                fm
            }

            1 -> {
                val fm = NewsFragment()
                fm
            }

            else -> {
                val fm = CurrencyFragment()
                fm
            }
        }
    }
}