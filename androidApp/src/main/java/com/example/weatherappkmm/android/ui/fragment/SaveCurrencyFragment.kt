package com.example.weatherappkmm.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappkmm.android.App
import com.example.weatherappkmm.android.databinding.FragmentSaveCurrencyBinding
import com.example.weatherappkmm.android.ui.adapter.CurrencyAdapter
import kotlinx.coroutines.launch

class SaveCurrencyFragment : Fragment() {

    private lateinit var adapter: CurrencyAdapter

    private lateinit var binding: FragmentSaveCurrencyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        lifecycleScope.launch {
            if (App.database?.getList()?.isNotEmpty() == true)
                App.database?.getList()?.let { adapter.setData(it) }
        }
    }


    private fun initView() {
        adapter = CurrencyAdapter()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter

        val swipe = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = adapter.getItemBuyPosition(position)

                lifecycleScope.launch {
                    App.database?.remove(item)
                    adapter.removeItem(item)
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipe)
        touchHelper.attachToRecyclerView(binding.rv)
    }
}