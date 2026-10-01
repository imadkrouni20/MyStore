package com.example.mystore.data

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val modelPath: String = ""   // مسار ملف glb (رابط أو ملف محلي)
)
