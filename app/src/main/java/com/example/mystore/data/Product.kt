package com.example.mystore.data

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,   // رابط صورة (نستخدم Coil لاحقاً أو نكتفي بالاسم)
    val category: String
)