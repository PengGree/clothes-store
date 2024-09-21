package com.example.mypixel.Clocth
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FakeProductViewModel : ViewModel() {
    private val _fakeProductList = mutableStateListOf<FakeProduct>()
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    var isSorted: Boolean by mutableStateOf(false)
    var stateFakeProduct: List<FakeProduct> by mutableStateOf(listOf())

    val fakeProductList: List<FakeProduct>
        get() = _fakeProductList

    fun getfakeProductList() {
        viewModelScope.launch {
            isLoading = true
            val fakeProductService = FakeProductService.getInstance()
            try {
                _fakeProductList.clear()
                val products = fakeProductService.getFakeProducts()
                _fakeProductList.addAll(products)
                stateFakeProduct = products
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

    fun toggleSort() {
        val sortedList = if (isSorted) {
            _fakeProductList.sortedByDescending { it.price }
        } else {
            _fakeProductList.sortedBy { it.price }
        }
        stateFakeProduct = sortedList.distinctBy { it.id }
        isSorted = !isSorted
    }
}
