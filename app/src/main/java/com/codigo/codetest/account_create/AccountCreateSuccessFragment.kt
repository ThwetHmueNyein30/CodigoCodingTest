package com.codigo.codetest.account_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.codigo.codetest.R
import com.codigo.codetest.databinding.FragmentAccountCreateSuccessBinding

class AccountCreateSuccessFragment : Fragment() {
    private lateinit var binding: FragmentAccountCreateSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountCreateSuccessBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.ok.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}