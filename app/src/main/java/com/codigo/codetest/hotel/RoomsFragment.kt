package com.codigo.codetest.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.codigo.codetest.databinding.FragmentRoomsBinding
import com.codigo.codetest.hotel.adapter.RoomsAdapter


class RoomsFragment : Fragment() {
    private lateinit var binding: FragmentRoomsBinding
    private lateinit var roomsAdapter: RoomsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        roomsAdapter = RoomsAdapter(
            arrayOf(
                "https://www.kayak.com/rimg/himg/f8/51/ac/revato-4210-12497415-817761.jpg?width=1366&height=768&crop=true",
                "https://cf.bstatic.com/xdata/images/hotel/max1024x768/219481913.jpg?k=ab8c126d9a5f33be24916460ee487bd70cd6769c12f39de3aaf6f3d71141760d&o=&hp=1",
                "https://hi-cdn.t-rp.co.uk/images/hotels/321634/11?width=870&height=480&crop=false"
            )
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = roomsAdapter
        }
    }
}