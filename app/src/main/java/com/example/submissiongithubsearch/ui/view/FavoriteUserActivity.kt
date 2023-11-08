package com.example.submissiongithubsearch.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiongithubsearch.R
import com.example.submissiongithubsearch.data.database.FavoriteUserItem
import com.example.submissiongithubsearch.databinding.ActivityFavoriteUserBinding
import com.example.submissiongithubsearch.ui.adapter.FavoriteListAdapter
import com.example.submissiongithubsearch.ui.manager.FavoriteManager

class FavoriteUserActivity : AppCompatActivity() {
    private lateinit var favoriteManager: FavoriteManager
    private lateinit var favoriteListAdapter: FavoriteListAdapter
    private lateinit var binding: ActivityFavoriteUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteManager = FavoriteManager(this)
        favoriteListAdapter = FavoriteListAdapter(favoriteManager.getFavoriteItems(), ::deleteItem)

        val rvFavoriteUserItem = binding.rvFavorite

        val layoutManager = LinearLayoutManager(this)
        rvFavoriteUserItem.layoutManager = layoutManager
        rvFavoriteUserItem.setHasFixedSize(true)
        rvFavoriteUserItem.adapter = favoriteListAdapter


    }

    private fun deleteItem(item: FavoriteUserItem){
        favoriteManager.deleteItem(item)
        favoriteListAdapter.updateData(item)
    }
}