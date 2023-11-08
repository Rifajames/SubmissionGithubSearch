package com.example.submissiongithubsearch.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiongithubsearch.data.response.ItemsItem
import com.example.submissiongithubsearch.databinding.ActivityMainBinding
import com.example.submissiongithubsearch.ui.adapter.GithubMainAdapters
import com.example.submissiongithubsearch.ui.settings.SettingPreferences
import com.example.submissiongithubsearch.ui.settings.dataStore
import com.example.submissiongithubsearch.ui.viewmodel.MainViewModel
import com.example.submissiongithubsearch.ui.viewmodel.ViewModelFactory

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

        binding.btnFavorite.setOnClickListener {
            val intentFavorite = Intent(this, FavoriteUserActivity::class.java)
            startActivity(intentFavorite)
        }
    }

    private fun observeData(){
        val pref = SettingPreferences.getInstance(application.dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            MainViewModel::class.java
        )
        mainViewModel.listUsers.observe(this){github ->
            setGithubData(github)
        }
        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
        mainViewModel.getThemeSettings().observe(this) {isDarkModeActive: Boolean ->
            if(isDarkModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.swithTheme.isChecked = true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.swithTheme.isChecked = false
            }
        }
        binding.svSearchUser.editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            mainViewModel.findUsers(binding.svSearchUser.editText.text.toString())
            false
        })
        binding.swithTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            mainViewModel.saveThemeSetting(isChecked)
        }
    }

    private fun setGithubData(data: List<ItemsItem>){
        val adapter = GithubMainAdapters()
        adapter.submitList(data)
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