package com.example.usersgithub.adapter


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.usersgithub.ui.FollowersFragment
import com.example.usersgithub.ui.FollowingFragment


class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowingFragment()
            1 -> fragment = FollowersFragment()
        }
        return fragment as Fragment
    }

}