package com.example.submissiongithubsearch.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissiongithubsearch.data.response.ResponseFollow
import com.example.submissiongithubsearch.databinding.ItemFollowBinding
import com.example.submissiongithubsearch.ui.view.DetailActivity

class FollowListAdapter(private val context: Context): RecyclerView.Adapter<FollowListAdapter.MyViewHolder>() {
    private var followList: List<ResponseFollow> = listOf()

    class MyViewHolder(val binding: ItemFollowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(responseFollow: ResponseFollow, context: Context){
            binding.tvFollow.text = responseFollow.login
            Glide.with(context).load(responseFollow.avatarUrl).into(binding.ivFollow)
            binding.itemViewFollow.setOnClickListener {
                val intentDetail = Intent(context, DetailActivity::class.java)
                intentDetail.putExtra("username", responseFollow.login)
                context.startActivity(intentDetail)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowListAdapter.MyViewHolder {
        val binding = ItemFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowListAdapter.MyViewHolder, position: Int) {
        val follow = followList[position]
        holder.bind(follow, context)
    }

    override fun getItemCount(): Int = followList.size

    fun setFollowList(data: List<ResponseFollow>?) {
        if (data != null) {
            followList = data
        }
        notifyDataSetChanged()
    }
}