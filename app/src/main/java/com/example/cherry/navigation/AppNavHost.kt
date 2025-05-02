package com.example.cherry.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cherry.ui.theme.screens.SplashScreen
import com.example.cherry.ui.theme.screens.dashboard.DashboardScreen
import com.example.cherry.ui.theme.screens.login.LoginScreen
import com.example.cherry.ui.theme.screens.signup.SignUpScreen

@Composable
fun AppNavHost(navController:NavHostController = rememberNavController(),startDestination:String= ROUTE_SPLASH){
    NavHost(navController=navController,startDestination=startDestination){
        composable(ROUTE_SPLASH){ SplashScreen {
            navController.navigate(ROUTE_SIGNUP) {
                popUpTo(ROUTE_SPLASH){inclusive=true}} }}
        composable(ROUTE_DASHBOARD){ DashboardScreen(navController) }
        composable(ROUTE_SIGNUP) { SignUpScreen(navController) }
        composable (ROUTE_LOGIN) {LoginScreen(navController) }

        }
    }

