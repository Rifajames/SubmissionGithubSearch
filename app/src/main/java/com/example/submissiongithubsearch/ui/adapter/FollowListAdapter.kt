package com.example.submissiongithubsearch.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissiongithubsearch.data.response.ResponseFollow
import com.example.submissiongithubsearch.databinding.ItemFollowBinding
import com.example.submissiongithubsearch.ui.view.DetailActivity

class FollowListAdapter(private val context: Context) :
    ListAdapter<ResponseFollow, FollowListAdapter.MyViewHolder>(FollowDiffCallback) {

    class MyViewHolder(val binding: ItemFollowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(responseFollow: ResponseFollow, context: Context) {
            binding.tvFollow.text = responseFollow.login
            Glide.with(context).load(responseFollow.avatarUrl).into(binding.ivFollow)
            binding.itemViewFollow.setOnClickListener {
                val intentDetail = Intent(context, DetailActivity::class.java)
                intentDetail.putExtra("username", responseFollow.login)
                context.startActivity(intentDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val follow = getItem(position)
        holder.bind(follow, context)
    }

    companion object {
        val FollowDiffCallback = object : DiffUtil.ItemCallback<ResponseFollow>() {
            override fun areItemsTheSame(
                oldItem: ResponseFollow,
                newItem: ResponseFollow
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseFollow,
                newItem: ResponseFollow
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}