package com.example.submissiongithubsearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submissiongithubsearch.data.database.FavoriteUserItem

class FavoriteUserViewModel : ViewModel(){
    private var _userFavorite = MutableLiveData<List<FavoriteUserItem>?>()
    val userFavorite: LiveData<List<FavoriteUserItem>?> = _userFavorite

    fun addToFavorite(item: FavoriteUserItem){
    }
}