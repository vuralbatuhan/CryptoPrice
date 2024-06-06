package com.example.cryptopriceapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptopriceapp.databinding.CryptoRecyclerRowBinding
import com.example.cryptopriceapp.model.CryptoModel

class CryptoRecyclerAdapter(val cryptoList: ArrayList<CryptoModel>) : RecyclerView.Adapter<CryptoRecyclerAdapter.CryptoViewHolder>() {

    class CryptoViewHolder(var view: CryptoRecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding : CryptoRecyclerRowBinding = CryptoRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    fun updateCryptos(newCryptoList: List<CryptoModel>) {
        cryptoList.clear()
        cryptoList.addAll(newCryptoList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.view.cryptoCurrency.text = cryptoList[position].currency
        holder.view.cryptoPrice.text = cryptoList[position].price + " $"
        if (position % 2 == 0){
            holder.itemView.setBackgroundColor(Color.parseColor("#9CBBCFC4"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#9C21A55C"))
        }

    }

}