package ru.axdar.finstart.screens.portfolio

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PortfolioPagerAdapter(f: Fragment, val list: List<Fragment>) : FragmentStateAdapter(f) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }

}