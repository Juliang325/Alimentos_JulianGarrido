package com.example.alimentos_juliangarrido.UI.fragments.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.alimentos_juliangarrido.UI.fragments.viewpager.tipo.TiposFragment

class ViewPagerAdapater(fragment: Fragment) : FragmentStateAdapter(fragment) {


    val list: List<Fragment> = listOf(HomeFragment(), TiposFragment())

    override fun createFragment(position: Int): Fragment {
        return list.get(position)


    }

    override fun getItemCount(): Int = list.size
}
