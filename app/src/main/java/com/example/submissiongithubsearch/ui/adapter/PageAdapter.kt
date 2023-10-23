package com.example.submissiongithubsearch.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submissiongithubsearch.ui.view.FollowersFragment
import com.example.submissiongithubsearch.ui.view.FollowingFragment

class PageAdapter(activity: AppCompatActivity, val username: String): FragmentStateAdapter(activity){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        val bundle = Bundle()
        bundle.putString("username", username)
        fragment?.arguments = bundle
        return fragment as Fragment
    }
}