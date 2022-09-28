package com.example.weatherappkmm.android.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappkmm.android.databinding.ItemLayoutBinding
import com.example.weatherappkmm.network.response.news.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.VH>() {

    private var positionOfProduct: Int = 0

    private lateinit var binding: ItemLayoutBinding

    private var newsList = mutableListOf<Article>()

    private var isAdmin: Boolean = false

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Article>) {
        newsList.clear()
        newsList.addAll(data)
        notifyDataSetChanged()
    }

    fun submitData(productData: Article) {
        newsList.add(productData)
        notifyItemInserted(newsList.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(productData: Article) {
        newsList[positionOfProduct] = productData
        notifyDataSetChanged()
    }

    fun setAdmin(admin: Boolean) {
        isAdmin = admin
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflate = LayoutInflater.from(parent.context)
        binding = ItemLayoutBinding.inflate(inflate, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(newsList[position])

    override fun getItemCount(): Int = newsList.size

    inner class VH(private val bindingFood: ItemLayoutBinding) :
        RecyclerView.ViewHolder(bindingFood.root) {

        fun bind(data: Article) {
            bindingFood.tvAuthorName.text = if(data.authorNews!=null) data.authorNews else "William Shakespeare"
            bindingFood.tvTitle.text = data.titleNews
            bindingFood.tvDescription.text = data.descriptionNews
        }
    }
}