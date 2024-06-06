package com.example.cryptopriceapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CryptoModel(

    @ColumnInfo(name = "currency")
    val currency : String,

    @ColumnInfo(name = "price")
    val price : String
)
