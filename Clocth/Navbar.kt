package com.example.mypixel

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Navbar() {
    Surface(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        color = Color.Gray, // Example background color
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(text = "Home", textColor = Color.White)
            NavItem(text = "Product", textColor = Color.White)
            NavItem(text = "Contact", textColor = Color.White)
        }
    }
}

@Composable
fun NavItem(text: String, textColor: Color) {
    Text(
        text = text,
        color = textColor,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

