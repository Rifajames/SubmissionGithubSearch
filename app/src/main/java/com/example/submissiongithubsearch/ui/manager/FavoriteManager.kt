package com.example.submissiongithubsearch.ui.manager

import android.content.Context
import com.example.submissiongithubsearch.data.database.FavoriteUserItem
import com.google.gson.Gson

class FavoriteManager(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("MyFavorites", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun addToFavorite(item: FavoriteUserItem?){
        val favoriteUserItem = getFavoriteItems()
        if(item != null){
            favoriteUserItem.add(item)
        }
        saveCartItems(favoriteUserItem)
    }

    fun getFavoriteItems(): MutableList<FavoriteUserItem>{
        val json = sharedPreferences.getString("FavoriteUserItem", null)
        return if(json != null){
            gson.fromJson(json, Array<FavoriteUserItem>::class.java).toMutableList()
        }else{
            mutableListOf()
        }
    }

    private fun saveCartItems(items: List<FavoriteUserItem>) {
        val json = gson.toJson(items)
        sharedPreferences.edit().putString("CartItems", json).apply()
    }

    fun clearCart() {
        sharedPreferences.edit().remove("CartItems").apply()
    }

    fun deleteItem(cartItem: FavoriteUserItem) {
        val cartItems = getFavoriteItems() // dapetin list
        cartItems.remove(cartItem) // list itu mau remove yang lu klik delete
        saveCartItems(cartItems) // list nya udah baru (-) ke delet, lu update share pref lewat sini
    }
}
