package com.example.cherry.ui.theme.screens.products
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cherry.data.ProductViewModel
import com.example.cherry.R

@Composable
fun AddproductScreen(navController: NavController) {
    val context = LocalContext.current
    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imageUri.value = it }
    }
    var productname by remember { mutableStateOf("") }
    var productprice by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    val productViewModel: ProductViewModel = viewModel()

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Product image display
        Card(
            shape = CircleShape,
            modifier = Modifier.padding(10.dp).size(200.dp)
        ) {
            AsyncImage(
                model = imageUri.value ?: R.drawable.ic_person,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp).clickable { launcher.launch("image/*") }
            )
        }
        Text(text = "Attach product image")

        // Product name input
        OutlinedTextField(
            value = productname,
            onValueChange = { newProductname -> productname = newProductname },
            label = { Text(text = "Product Name") },
            placeholder = { Text(text = "Please enter product name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Product price input
        OutlinedTextField(
            value = productprice,
            onValueChange = { newProductprice -> productprice = newProductprice },
            label = { Text(text = "Unit Product Price") },
            placeholder = { Text(text = "Please enter unit product price") },
            modifier = Modifier.fillMaxWidth()
        )

        // Product description input
        OutlinedTextField(
            value = desc,
            onValueChange = { newDesc -> desc = newDesc },
            label = { Text(text = "Brief description") },
            placeholder = { Text(text = "Please enter product description") },
            modifier = Modifier.fillMaxWidth().height(150.dp),
            singleLine = false
        )

        // Buttons for navigation and saving the product
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                // Navigate to the All Products screen
                navController.navigate("view_products_route") // Replace with your route
            }) {
                Text(text = "All Products")
            }

            Button(onClick = {
                // Check if the image is picked and proceed with uploading the product
                imageUri.value?.let {
                    // Convert Uri to String (path or URL)
                    val imageUrl = it.toString() // Modify this to upload to Imgur and get URL if needed

                    // Pass the correct parameters: name, price, desc, imageUrl
                    productViewModel.uploadProductWithImage(
                        it, context, productname, productprice, desc, imageUrl, navController)
                } ?: Toast.makeText(context, "Please pick an image", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "SAVE")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddproductScreenPreview() {
    AddproductScreen(rememberNavController())
}
