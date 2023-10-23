package com.example.submissiongithubsearch.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiongithubsearch.data.response.ItemsItem
import com.example.submissiongithubsearch.databinding.ActivityMainBinding
import com.example.submissiongithubsearch.ui.adapter.GithubMainAdapter
import com.example.submissiongithubsearch.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        showLoading(false)
        with(binding){
            svSearchUser.setupWithSearchBar(sbSearchUser)
            rvUserList.layoutManager= layoutManager
        }
        observeData()
    }

    private fun observeData(){
        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java
        )
        mainViewModel.listUsers.observe(this){github ->
            setGithubData(github)
        }
        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
        binding.svSearchUser.editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            mainViewModel.findUsers(binding.svSearchUser.editText.text.toString())
            false
        })
    }

    private fun setGithubData(response: List<ItemsItem>){
        val adapter = GithubMainAdapter(this)
        adapter.setItems(response)
        binding.rvUserList.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.mainProgressBar.visibility = View.VISIBLE
            binding.rvUserList.visibility = View.GONE
        } else {
            binding.mainProgressBar.visibility = View.GONE
            binding.rvUserList.visibility = View.VISIBLE
        }
    }
}