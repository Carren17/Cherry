package com.example.cherry.ui.theme.screens.products

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cherry.R
import com.example.cherry.data.ProductViewModel
import com.example.cherry.models.ProductModel
import com.google.firebase.database.*

@Composable
fun UpdateproductScreen(navController: NavController, productId: String) {
    val context = LocalContext.current
    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val imageUrl = remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imageUri.value = it }
    }

    var productname by remember { mutableStateOf("") }
    var productprice by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    val currentDataRef = FirebaseDatabase.getInstance().getReference("Products/$productId")

    DisposableEffect(Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val product = snapshot.getValue(ProductModel::class.java)
                product?.let {
                    productname = it.name
                    productprice = it.price
                    desc = it.desc
                    imageUrl.value = it.image
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
            }
        }
        currentDataRef.addValueEventListener(listener)
        onDispose { currentDataRef.removeEventListener(listener) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp)
        ) {
            AsyncImage(
                model = imageUri.value ?: imageUrl.value.ifEmpty { R.drawable.ic_person },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clickable { launcher.launch("image/*") }
            )
        }

        Text(text = "Attach product image")

        OutlinedTextField(
            value = productname,
            onValueChange = { productname = it },
            label = { Text("Product Name") },
            placeholder = { Text("Please enter product name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = productprice,
            onValueChange = { productprice = it },
            label = { Text("Unit Product Price") },
            placeholder = { Text("Please enter unit product price") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = desc,
            onValueChange = { desc = it },
            label = { Text("Brief description") },
            placeholder = { Text("Please enter product description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            singleLine = false
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                navController.navigate("view_products_route") // Replace with your actual route constant
            }) {
                Text("All Products")
            }

            Button(onClick = {
                val productRepository = ProductViewModel()
                productRepository.updateProduct(
                    context = context,
                    navController = navController,
                    name = productname,
                    price = productprice,
                    desc = desc,
                    productId = productId,
                    image = imageUrl.value // <- Fix: passing image
                )
            }) {
                Text("UPDATE")
            }
        }
    }
}
