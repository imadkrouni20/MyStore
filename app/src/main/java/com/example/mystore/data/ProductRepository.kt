package com.example.mystore.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductRepository(context: Context) {
    private val prefs = context.getSharedPreferences("mystore_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getProducts(): MutableList<Product> {
        val json = prefs.getString("products", null)
        return if (json.isNullOrEmpty()) {
            sampleProducts.toMutableList().also { saveProducts(it) }
        } else {
            val type = object : TypeToken<MutableList<Product>>() {}.type
            gson.fromJson<MutableList<Product>>(json, type) ?: mutableListOf()
        }
    }

    fun saveProducts(products: List<Product>) {
        prefs.edit().putString("products", gson.toJson(products)).apply()
    }

    fun addProduct(product: Product) {
        val list = getProducts()
        val newId = (list.maxOfOrNull { it.id } ?: 0) + 1
        list.add(product.copy(id = newId))
        saveProducts(list)
    }

    fun updateProduct(product: Product) {
        val list = getProducts()
        val index = list.indexOfFirst { it.id == product.id }
        if (index >= 0) {
            list[index] = product
            saveProducts(list)
        }
    }

    fun deleteProduct(id: Int) {
        val list = getProducts()
        list.removeAll { it.id == id }
        saveProducts(list)
    }
}
