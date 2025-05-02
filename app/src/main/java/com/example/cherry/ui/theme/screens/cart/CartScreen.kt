package com.example.cherry.ui.theme.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CartItem(val name: String, val price: Double)

@Composable
fun CartScreen() {
    var cartItems by remember {
        mutableStateOf(
            listOf(
                CartItem("Corset Dress", 129.99),
                CartItem("Satin Gown", 179.50),
                CartItem("Lace Bodysuit", 89.00)
            )
        )
    }

    val total = cartItems.sumOf { it.price }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Your Cart",
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartItems) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item.name, fontSize = 18.sp)
                    Text(text = "$${"%.2f".format(item.price)}", fontSize = 18.sp)
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("$${"%.2f".format(total)}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        // Checkout Button
        Button(
            onClick = { /* Checkout logic here */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD4A5A5),
                contentColor = Color.White
            )
        ) {
            Text("Checkout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen()
}
