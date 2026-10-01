package com.example.mystore.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.data.Product
import com.example.mystore.data.orderViaWhatsApp
import com.example.mystore.ui.ModelViewer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    products: List<Product>,
    onManageClick: () -> Unit
) {
    val context = LocalContext.current

    if (products.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "لا توجد سلع",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onManageClick) { Text("إدارة السلع") }
            }
        }
        return
    }

    val pagerState = rememberPagerState(pageCount = { products.size })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
            // ✅ userScrollEnabled محذوف = السحب مفعّل افتراضياً
        ) { page ->
            val product = products[page]
            ProductFullPage(
                product = product,
                onOrder = { orderViaWhatsApp(context, product) }
            )
        }

        // زر إدارة السلع (أسفل يسار)
        FloatingActionButton(
            onClick = onManageClick,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            Icon(Icons.Default.Settings, contentDescription = "إدارة السلع")
        }

        // مؤشر الصفحة (أسفل يمين)
        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Text(
                text = "${pagerState.currentPage + 1} / ${products.size}",
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun ProductFullPage(product: Product, onOrder: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ═══════════════════════════════════════════
        // عارض ثلاثي الأبعاد
        // ═══════════════════════════════════════════
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.3f)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            if (product.modelPath.isNotEmpty()) {
                ModelViewer(modelPath = product.modelPath)
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = product.name.firstOrNull()?.toString() ?: "?",
                        fontSize = 120.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ═══════════════════════════════════════════
        // معلومات السلعة
        // ═══════════════════════════════════════════
        Text(
            text = product.name,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = product.category,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "${product.price} د.م",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = product.description,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 21.sp,
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onOrder,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "اطلب الآن",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
