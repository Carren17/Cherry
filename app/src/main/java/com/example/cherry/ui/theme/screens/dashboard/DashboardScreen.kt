package com.example.cherry.ui.theme.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cherry.R


@Composable
fun DashboardScreen(
    onGoToWishlist: () -> Unit,
    onGoToCart: () -> Unit,
    onLogOut: () -> Unit
) {
    Column {
        Button(onClick = { onGoToWishlist() }) {
            Text("Go to Wishlist")
        }
        Button(onClick = { onGoToCart() }) {
            Text("Go to Cart")
        }
        Button(onClick = { onLogOut() }) {
            Text("Log Out")
        }
    }
}


@Composable
fun DashboardScreen(navController: NavController,userName: String = "Welcome Back!") {
    val elegantFont = FontFamily.Serif
    val backgroundColor = Color.White
    val accentColor = Color(0xFFD4A5A5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Dashboard Logo",
            modifier = Modifier
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = userName,
            fontFamily = elegantFont,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* TODO: Navigate to product section or details */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = accentColor,
                contentColor = Color.White
            )
        ) {
            Text("Explore Collection", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Handle logout or profile action */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = accentColor
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp)
        ) {
            Text("Log Out", fontSize = 16.sp)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(rememberNavController())
}
