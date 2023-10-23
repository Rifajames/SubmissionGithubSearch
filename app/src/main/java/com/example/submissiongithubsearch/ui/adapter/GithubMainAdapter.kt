package com.example.submissiongithubsearch.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissiongithubsearch.data.response.ItemsItem
import com.example.submissiongithubsearch.databinding.ItemUserBinding
import com.example.submissiongithubsearch.ui.view.DetailActivity

class GithubMainAdapter(private val context: Context): RecyclerView.Adapter<GithubMainAdapter.MyViewHolder>() {

    private var items: List<ItemsItem> = listOf()
    class MyViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(responseGithub: ItemsItem, context: Context){
            binding.tvUserName.text = responseGithub.login
            Glide.with(context).load(responseGithub.avatar_url).into(binding.avatarImageView)
            binding.itemView.setOnClickListener {
                val intentDetail = Intent(binding.root.context, DetailActivity::class.java)
                intentDetail.putExtra("username", responseGithub.login)
                binding.root.context.startActivity(intentDetail)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GithubMainAdapter.MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubMainAdapter.MyViewHolder, position: Int) {
        val review = items[position]
        holder.bind(review, context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(data: List<ItemsItem>){
        items = data
        notifyDataSetChanged()
    }
}