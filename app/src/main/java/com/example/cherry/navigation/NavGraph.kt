package com.example.cherry.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cherry.ui.theme.screens.dashboard.DashboardScreen
import com.example.cherry.ui.theme.screens.login.LoginScreen
import com.example.cherry.ui.theme.screens.wishlist.WishlistScreen
import com.example.cherry.ui.theme.screens.cart.CartScreen
import com.example.cherry.ui.theme.screens.signup.SignUpScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "signup") {
        composable("signup") {
            SignUpScreen(onSignUp = { _, _ -> navController.navigate("dashboard") })
        }
        composable("login") {
            LoginScreen(onLogin = { _, _ -> navController.navigate("dashboard") })
        }
        composable("dashboard") {
            DashboardScreen(
                onGoToWishlist = { navController.navigate("wishlist") },
                onGoToCart = { navController.navigate("cart") },
                onLogOut = { navController.navigate("signup") }
            )
        }
        composable("wishlist") {
            WishlistScreen()
        }
        composable("cart") {
            CartScreen()
        }
    }
}
