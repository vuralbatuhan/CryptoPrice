package com.example.cryptopriceapp.service

import com.example.cryptopriceapp.model.CryptoModel
import retrofit2.http.GET

interface CryptoAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getCrypto() : List<CryptoModel>
}