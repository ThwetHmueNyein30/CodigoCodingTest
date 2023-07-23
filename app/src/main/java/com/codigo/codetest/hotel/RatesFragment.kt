package com.codigo.codetest.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.codigo.codetest.databinding.FragmentRatesBinding
import com.codigo.codetest.hotel.adapter.RatesAdapter

class RatesFragment : Fragment() {
    private lateinit var binding: FragmentRatesBinding
    private lateinit var ratesAdapter: RatesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRatesBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        ratesAdapter = RatesAdapter(arrayOf("A","B","C"))
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ratesAdapter
        }
    }
}