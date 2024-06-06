package com.example.cryptopriceapp.service

import com.example.cryptopriceapp.model.CryptoModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoApiService {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoAPI::class.java)

    suspend fun getData() : List<CryptoModel> {
        return retrofit.getCrypto()
    }
}