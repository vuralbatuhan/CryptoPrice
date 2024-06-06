package com.example.cryptopriceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptopriceapp.R
import com.example.cryptopriceapp.adapter.CryptoRecyclerAdapter
import com.example.cryptopriceapp.databinding.FragmentCryptoListBinding
import com.example.cryptopriceapp.model.CryptoModel
import com.example.cryptopriceapp.service.CryptoAPI
import com.example.cryptopriceapp.viewModel.CryptoListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoListFragment : Fragment() {

    private var _binding : FragmentCryptoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : CryptoListViewModel
    private  val cryptoRecyclerAdapter = CryptoRecyclerAdapter(arrayListOf())
    private var cryptoListFull = arrayListOf<CryptoModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCryptoListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CryptoListViewModel::class.java]
        viewModel.getCryptosEthernet()

        binding.cryptoRV.layoutManager = LinearLayoutManager(context)
        binding.cryptoRV.adapter = cryptoRecyclerAdapter
        binding.cryptoRV.visibility = View.VISIBLE

        observeLiveData()
        setupSearchView()

    }

    fun observeLiveData() {
        viewModel.cryptos.observe(viewLifecycleOwner, { cryptoList ->
            cryptoList?.let {
                cryptoListFull = ArrayList(it)
                cryptoRecyclerAdapter.updateCryptos(it)
            }
        })
    }

    private fun setupSearchView() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText ?: "")
                return true
            }
        })
    }

    private fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            cryptoListFull
        } else {
            cryptoListFull.filter {
                it.currency.contains(query, ignoreCase = true)
            }
        }
        cryptoRecyclerAdapter.updateCryptos(filteredList)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}