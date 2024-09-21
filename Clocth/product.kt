
package com.example.mypixel.Clocth


data class FakeProduct (
    val id: Long,
    val image: String,
    val price: Double,
    val rating: Rating,
    val description: String,
    val title: String,
    val category: String
)

data class Rating (
    val rate: Double,
    val count: Long
)
