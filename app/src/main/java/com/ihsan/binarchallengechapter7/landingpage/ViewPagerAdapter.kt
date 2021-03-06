package com.ihsan.binarchallengechapter7.landingpage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity,
                       listener: (CharSequence) -> Unit): FragmentStateAdapter(fa) {

    private val dataFragments = mutableListOf(
        SecondFragment.newInstance("Bermain suit melawan sesama pemain."),
        SecondFragment.newInstance("Bermain suit melawan komputer.")
    )
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = dataFragments[position]
}