package com.example.cryptopriceapp.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptopriceapp.model.CryptoModel
import com.example.cryptopriceapp.service.CryptoApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoListViewModel(application : Application) : AndroidViewModel(application) {

    val cryptos = MutableLiveData<List<CryptoModel>>()

    private val cryptoApiService = CryptoApiService()

    fun getCryptosEthernet() {
        viewModelScope.launch(Dispatchers.IO) {
            val  cryptoss = cryptoApiService.getData()
            withContext(Dispatchers.Main) {
                cryptos.value = cryptoss
            }
        }
    }

}