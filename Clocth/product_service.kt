package com.example.mypixel.Clocth

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://fakestoreapi.com/"

interface FakeProductService{
    @GET("products")
    suspend fun getFakeProducts(): List<FakeProduct>

    companion object {
        var service : FakeProductService? = null
        fun getInstance(): FakeProductService {
            if(service == null){
                service = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FakeProductService::class.java)
            }
            return service!!
        }
    }
}

