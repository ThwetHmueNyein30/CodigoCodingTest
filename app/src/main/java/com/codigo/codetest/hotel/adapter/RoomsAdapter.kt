package com.codigo.codetest.hotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigo.codetest.databinding.ItemRoomBinding
import com.squareup.picasso.Picasso

class RoomsAdapter(private val data: Array<String>) :
    RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    private lateinit var binding: ItemRoomBinding

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemRoomBinding.inflate(LayoutInflater.from(viewGroup.context))

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(data[position]).into(binding.imageView);
    }

    override fun getItemCount() = data.size

}