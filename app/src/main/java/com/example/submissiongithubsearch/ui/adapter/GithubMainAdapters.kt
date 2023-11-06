package com.example.submissiongithubsearch.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissiongithubsearch.data.response.ItemsItem
import com.example.submissiongithubsearch.databinding.ItemUserBinding
import com.example.submissiongithubsearch.ui.view.DetailActivity

class GithubMainAdapters : ListAdapter<ItemsItem, GithubMainAdapters.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemUserBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(responseGithub: ItemsItem){
            binding.tvUserName.text = responseGithub.login
            Glide.with(context).load(responseGithub.avatar_url).into(binding.avatarImageView)
            binding.itemView.setOnClickListener{
                val intentDetail = Intent(binding.root.context, DetailActivity::class.java)
                intentDetail.putExtra("username", responseGithub.login)
                binding.root.context.startActivity(intentDetail)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}