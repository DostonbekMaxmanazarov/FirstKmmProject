package com.example.weatherappkmm.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherappkmm.android.ui.adapter.MainPageAdapter
import com.example.weatherappkmm.android.R
import com.example.weatherappkmm.android.databinding.FragmentMainBinding
import com.example.weatherappkmm.android.extensions.onPageChangeCallback

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var pagerAdapter: MainPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBottomNavigation()

    }

    private fun initView() {
        pagerAdapter = MainPageAdapter(requireActivity())
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.onPageChangeCallback {
            loadPageSelection(it)
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuWeather -> {
                    binding.viewPager.setCurrentItem(0, true)
                }
                R.id.menuNews -> {
                    binding.viewPager.setCurrentItem(1, true)
                }
                R.id.menuMoney -> {
                    binding.viewPager.setCurrentItem(2, true)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun loadPageSelection(position: Int) {
        binding.viewPager.currentItem = position
        when (position) {
            0 -> {
                binding.bottomNav.selectedItemId = R.id.menuWeather
            }
            1 -> {
                binding.bottomNav.selectedItemId = R.id.menuNews
            }
            2 -> {
                binding.bottomNav.selectedItemId = R.id.menuMoney
            }
        }
    }
}