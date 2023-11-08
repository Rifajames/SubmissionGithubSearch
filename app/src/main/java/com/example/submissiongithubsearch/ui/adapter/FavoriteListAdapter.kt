package com.example.submissiongithubsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submissiongithubsearch.R
import com.example.submissiongithubsearch.data.database.FavoriteUserItem
import com.example.submissiongithubsearch.databinding.ItemFavoriteBinding
import com.example.submissiongithubsearch.databinding.ItemFollowBinding

class FavoriteListAdapter(
    var listFavoriteUserItem: MutableList<FavoriteUserItem>,
    val clickDelete: (favoriteUserItem: FavoriteUserItem) -> Unit
) : RecyclerView.Adapter<FavoriteListAdapter.FavoriteItemViewHolder>(){

    class FavoriteItemViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindFavoriteItem(favoriteUserItem: FavoriteUserItem, clickDelete: (favoriteUserItem: FavoriteUserItem) -> Unit){
            binding.tvFavorite.text = favoriteUserItem.username
            binding.btnFavoriteDetails.setOnClickListener {
                clickDelete.invoke(favoriteUserItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteItemViewHolder(binding)
    }

    override fun getItemCount(): Int = listFavoriteUserItem.size

    override fun onBindViewHolder(holder: FavoriteItemViewHolder, position: Int) {
        holder.bindFavoriteItem(listFavoriteUserItem[position],clickDelete)
    }

    fun updateData(cartItem: FavoriteUserItem){
        listFavoriteUserItem.remove(cartItem)
        notifyDataSetChanged()
    }
}