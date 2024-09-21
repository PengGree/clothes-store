package com.example.mypixel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypixel.Clocth.DetailsPage
import com.example.mypixel.Clocth.FakeProduct
import com.example.mypixel.Clocth.FakeProductViewModel



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            AsyncApp()



        }
    }
}

@Composable
fun AsyncApp() {
    val navController = rememberNavController()
    val vm = remember { FakeProductViewModel() }

    NavHost(
        navController = navController,
        startDestination = "productList"
    ) {
        composable("productList") {
            FakeProduct(vm, navController)
        }
        composable("details/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toLongOrNull()
            val product = vm.fakeProductList.find { it.id == productId }
            DetailsPage(product, navController)
        }
    }
}


