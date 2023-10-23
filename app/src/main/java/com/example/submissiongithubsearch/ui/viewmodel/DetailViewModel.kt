package com.example.submissiongithubsearch.ui.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submissiongithubsearch.data.response.DetailResponse
import com.example.submissiongithubsearch.data.response.ItemsItem
import com.example.submissiongithubsearch.data.response.ResponseFollow
import com.example.submissiongithubsearch.data.response.ResponseGithub
import com.example.submissiongithubsearch.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _detail = MutableLiveData<DetailResponse?>()
    val detail: LiveData<DetailResponse?> = _detail

    private var _followers = MutableLiveData<List<ResponseFollow>?>()
    val followers: LiveData<List<ResponseFollow>?> = _followers

    private var _following = MutableLiveData<List<ResponseFollow>?>()
    val following: LiveData<List<ResponseFollow>?> = _following

    fun findGithubDetail(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getGithubDetail(query)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {

                if(response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null){
                        _detail.value = responseBody
                    }else{
                        Log.d(ContentValues.TAG, "Failure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "Error: ${t.message}")
            }
        })
    }

    fun findFollowers(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollower(query)
        client.enqueue(object : Callback<List<ResponseFollow>> {
            override fun onResponse(call: Call<List<ResponseFollow>>, response: Response<List<ResponseFollow>>) {

                if(response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null){
                        _followers.value = responseBody
                    }else{
                        Log.d(ContentValues.TAG, "Failure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<ResponseFollow>>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "Error: ${t.message}")
            }
        })
    }

    fun findFollowing(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(query)
        client.enqueue(object : Callback<List<ResponseFollow>> {
            override fun onResponse(call: Call<List<ResponseFollow>>, response: Response<List<ResponseFollow>>) {

                if(response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null){
                        _following.value = responseBody
                    }else{
                        Log.d(ContentValues.TAG, "Failure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<ResponseFollow>>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "Error: ${t.message}")
            }
        })
    }
}