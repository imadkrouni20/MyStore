package com.example.mystore.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

// ⚠️ غيّر رقم واتساب هنا (بصيغة دولية بدون + وبدون مسافات)
private const val WHATSAPP_NUMBER = "212638784945"

fun orderViaWhatsApp(context: Context, product: Product) {
    val message = "مرحباً، أريد طلب هذه السلعة:\n\n" +
            "الاسم: ${product.name}\n" +
            "الفئة: ${product.category}\n" +
            "الثمن: ${product.price} د.م"
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://wa.me/$WHATSAPP_NUMBER?text=${Uri.encode(message)}")
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "تعذّر فتح واتساب", Toast.LENGTH_SHORT).show()
    }
}
