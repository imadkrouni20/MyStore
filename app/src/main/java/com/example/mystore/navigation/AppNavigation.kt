package com.example.mystore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mystore.data.sampleProducts
import com.example.mystore.screens.HomeScreen
import com.example.mystore.screens.ProductDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                products = sampleProducts,
                onProductClick = { product ->
                    navController.navigate("detail/${product.id}")
                }
            )
        }
        composable(
            route = "detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            val product = sampleProducts.find { it.id == productId }
            ProductDetailScreen(
                product = product,
                onBack = { navController.popBackStack() }
            )
        }
    }
}