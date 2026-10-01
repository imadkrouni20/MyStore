package com.example.mystore.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mystore.data.ProductRepository
import com.example.mystore.screens.EditProductScreen
import com.example.mystore.screens.HomeScreen
import com.example.mystore.screens.ManageProductsScreen

@Composable
fun AppNavigation(repository: ProductRepository) {
    val navController = rememberNavController()
    var products by remember { mutableStateOf(repository.getProducts()) }

    fun refresh() {
        products = repository.getProducts()
    }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                products = products,
                onManageClick = { navController.navigate("manage") }
            )
        }

        composable("manage") {
            ManageProductsScreen(
                products = products,
                onBack = { navController.popBackStack() },
                onAdd = { navController.navigate("edit/0") },
                onEdit = { product -> navController.navigate("edit/${product.id}") },
                onDelete = { product ->
                    repository.deleteProduct(product.id)
                    refresh()
                }
            )
        }

        composable(
            route = "edit/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            val existing = if (productId == 0) null else products.find { it.id == productId }
            EditProductScreen(
                product = existing,
                onBack = { navController.popBackStack() },
                onSave = { product ->
                    if (existing == null) {
                        repository.addProduct(product)
                    } else {
                        repository.updateProduct(product)
                    }
                    refresh()
                    navController.popBackStack()
                }
            )
        }
    }
}
