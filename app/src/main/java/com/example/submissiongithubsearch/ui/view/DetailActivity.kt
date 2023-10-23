package com.example.submissiongithubsearch.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submissiongithubsearch.R
import com.example.submissiongithubsearch.data.response.DetailResponse
import com.example.submissiongithubsearch.databinding.ActivityDetailBinding
import com.example.submissiongithubsearch.ui.adapter.PageAdapter
import com.example.submissiongithubsearch.ui.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        detailViewModel.findGithubDetail(username!!)
        detailViewModel.detail.observe(this){
            updateUser(it)
        }
        val pageAdapter = PageAdapter(this, username)
        binding.viewPager.adapter = pageAdapter
        val tabDetails: TabLayout = binding.tabDetail
        TabLayoutMediator(tabDetails, binding.viewPager){ tabDetail, position ->
            tabDetail.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun updateUser(detail: DetailResponse?){
        Glide.with(binding.root.context).load(detail?.avatarUrl).into(binding.ivDetailUser)
        binding.tvFollowers.text = detail?.followers.toString()
        binding.tvFollowing.text = detail?.following.toString()
        binding.tvRepository.text = detail?.publicRepos.toString()
    }

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}