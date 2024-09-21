package com.example.mypixel

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Footer() {
    // Customize your footer content here
    Surface(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        color = Color.Gray, // Example background color
    ) {
        // Footer content goes here
        Text("Footer")
    }
}
