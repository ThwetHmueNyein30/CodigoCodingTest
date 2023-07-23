package com.codigo.codetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.codigo.codetest.databinding.FragmentTaskIntroBinding

class TaskIntroFragment : Fragment() {
    private lateinit var binding: FragmentTaskIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskIntroBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.state.setOnClickListener {
            findNavController().navigate(R.id.nav_login)
        }
        binding.code.setOnClickListener {
            findNavController().navigate(R.id.nav_movie)
        }
        binding.ui.setOnClickListener {
            findNavController().navigate(R.id.nav_hotel)
        }
    }
}