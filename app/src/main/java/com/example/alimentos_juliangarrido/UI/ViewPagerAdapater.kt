package com.example.alimentos_juliangarrido.UI

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.alimentos_juliangarrido.UI.fragments.HomeFragment
import com.example.alimentos_juliangarrido.UI.fragments.TiposFragment

class ViewPagerAdapater(fragment: Fragment) : FragmentStateAdapter(fragment) {


    val list: List<Fragment> = listOf(HomeFragment(),TiposFragment())

    override fun createFragment(position: Int): Fragment {
        return list.get(position)


    }

    override fun getItemCount(): Int = list.size
}
