package com.example.mypixel.Clocth
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPage(product: FakeProduct?, nc: NavController) {
    if (product == null) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Product not found", style = MaterialTheme.typography.headlineSmall, color = Color.Red)
        }
        return
    }

    Scaffold(
        topBar = {
            Surface(
                color = Color(0xFFD53F10),
                contentColor = Color.Black, // Adjust text color for contrast if needed
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = "Product Detail",
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { nc.navigate("productList") }) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            Surface(
                color = Color(0xFFD53F10), // Background color
                contentColor = Color.Black, // Adjust text color for contrast if needed
                modifier = Modifier.fillMaxWidth()
            ) {
                BottomAppBar(
                    content = {
                        Text(
                            text = "Footer Text",
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Enables vertical scrolling
                .padding(innerPadding)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color(0xFF01CFBC),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(horizontal = 25.dp, vertical = 3.dp)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.W500,
                        color = Color.DarkGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                        .padding(8.dp)
                )
            }
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50.dp))
                    .padding(15.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.category,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W400, color = Color.DarkGray),
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = NumberFormat.getCurrencyInstance(Locale.US).format(product.price),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 22.sp),
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = product.rating.rate.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 22.sp, fontWeight = FontWeight.W500),
                color = Color(0xFFDBBE22),
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
