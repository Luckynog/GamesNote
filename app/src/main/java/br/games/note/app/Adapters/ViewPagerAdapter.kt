package br.games.note.app.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private var fragmentList : MutableList<Fragment> = mutableListOf()
    private var titleList : MutableList<String> = mutableListOf()

    fun getTitle(position: Int) : String {
        return titleList[position]
    }

    fun addFragment(fragment: Fragment, title:String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getItemCount(): Int{
       return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}

