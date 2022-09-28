package com.example.weatherappkmm.android.extensions

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.onPageChangeCallback(block: (Int) -> Unit) {
    this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            block(position)
        }
    })
}