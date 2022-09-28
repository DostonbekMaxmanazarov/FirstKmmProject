package com.example.weatherappkmm.android.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappkmm.android.databinding.ItemLayoutCurrencyBinding
import com.example.weatherappkmm.sqldelight.CurrencyTable

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.VH>() {

    private var positionOfProduct: Int = 0

    private lateinit var binding: ItemLayoutCurrencyBinding

    private var newsList = mutableListOf<CurrencyTable>()

    private var isAdmin: Boolean = false

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<CurrencyTable>) {
        newsList.clear()
        newsList.addAll(data)
        notifyDataSetChanged()
    }

    fun removeItem(productData: CurrencyTable){
        val index = newsList.indexOfFirst {
            it.id == productData.id
        }
        newsList.removeAt(index)
        notifyItemRemoved(index)
    }
    fun submitData(productData: CurrencyTable) {
        newsList.add(productData)
        notifyItemInserted(newsList.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(productData: CurrencyTable) {
        newsList[positionOfProduct] = productData
        notifyDataSetChanged()
    }

    fun setAdmin(admin: Boolean) {
        isAdmin = admin
    }

    fun getItemBuyPosition(position:Int):CurrencyTable{
        return newsList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflate = LayoutInflater.from(parent.context)
        binding = ItemLayoutCurrencyBinding.inflate(inflate, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(newsList[position])

    override fun getItemCount(): Int = newsList.size

    inner class VH(private val bindingFood: ItemLayoutCurrencyBinding) :
        RecyclerView.ViewHolder(bindingFood.root) {

        fun bind(data: CurrencyTable) {
            bindingFood.tvCurrencyValue.text = data.result_value
        }
    }
}