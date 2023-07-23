package com.codigo.codetest.hotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigo.codetest.databinding.ItemRatesBinding

class RatesAdapter(private val data: Array<String>) :
    RecyclerView.Adapter<RatesAdapter.ViewHolder>() {
    private lateinit var binding: ItemRatesBinding

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemRatesBinding.inflate(LayoutInflater.from(viewGroup.context))

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {}

    override fun getItemCount() = data.size

}