package com.example.mypixel.Clocth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FakeProduct(vm: FakeProductViewModel, navController: NavController) {
    // State for search query
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        vm.getfakeProductList()}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Home Page") },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { /* Handle Home click */ }) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home"
                            )
                        }
                        // Uncomment and configure the following icon buttons as needed
                        // IconButton(onClick = { /* Handle Product click */ }) {
                        //     Icon(
                        //         imageVector = Icons.Default.ShoppingCart,
                        //         contentDescription = "Product"
                        //     )
                        // }
                        // IconButton(onClick = { /* Handle Contact click */ }) {
                        //     Icon(
                        //         imageVector = Icons.Default.ContactMail,
                        //         contentDescription = "Contact"
                        //     )
                        // }
                        IconButton(onClick = { vm.toggleSort() }) {
                            Icon(
                                imageVector = Icons.Default.SortByAlpha,
                                contentDescription = "Sort"
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                content = {
                    Text(
                        text = "Footer Text",
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                // Search bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { query -> searchQuery = query },
                    label = { Text("Search") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                if (vm.isLoading) {
                    CircularProgressIndicator()
                } else if (vm.errorMessage.isNotEmpty()) {
                    Text("Error: ${vm.errorMessage}")
                } else {
                    // Filtered products based on search query
                    val filteredProducts = vm.stateFakeProduct.filter { product ->
                        product.title.contains(searchQuery, ignoreCase = true)
                    }

                    LazyColumn {
                        items(filteredProducts) { item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .clickable { navController.navigate("details/${item.id}") }
                            ) {
                                Surface(
                                    modifier = Modifier.size(150.dp),
                                    color = Color.White,
                                ) {
                                    AsyncImage(
                                        model = item.image,
                                        contentDescription = item.image,
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                ) {
                                    Text(text = item.title)
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(text = item.price.toString())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
