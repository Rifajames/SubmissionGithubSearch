package com.example.submissiongithubsearch.ui.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.submissiongithubsearch.data.response.ItemsItem
import com.example.submissiongithubsearch.data.response.ResponseGithub
import com.example.submissiongithubsearch.data.retrofit.ApiConfig
import com.example.submissiongithubsearch.ui.settings.SettingPreferences
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class MainViewModel(private val pref: SettingPreferences): ViewModel() {
    private var _listUsers = MutableLiveData<List<ItemsItem>>()
    val listUsers : LiveData<List<ItemsItem>> = _listUsers

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainViewModel"
    }

    fun findUsers(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUser(query)
        client.enqueue(object : Callback<ResponseGithub> {
            override fun onResponse(call: Call<ResponseGithub>, response: Response<ResponseGithub>) {

                if(response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listUsers.value = responseBody.items
                    }else{
                        Log.d(ContentValues.TAG, "Failure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseGithub>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "Error: ${t.message}")
            }
        })
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}