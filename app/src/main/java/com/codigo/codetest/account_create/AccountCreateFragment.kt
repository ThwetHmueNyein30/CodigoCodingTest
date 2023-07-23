package com.codigo.codetest.account_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codigo.codetest.R
import com.codigo.codetest.databinding.FragmentAccountCreateBinding

class AccountCreateFragment : Fragment() {
    private lateinit var binding : FragmentAccountCreateBinding
    private val viewModel: AccountCreateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountCreateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setupUI()
        listeners()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupUI() {
        binding.bod.setOnClickListener {
            openDateChooser(requireContext(),viewModel.dateOfBirthForServer.value){
                viewModel.dobChange(it)
                binding.bod.setText(sdf.format(it))
            }
        }
        binding.male.setOnClickListener {
            binding.male.setBackgroundResource(R.drawable.selected_button_bg)
            binding.female.background = null
            viewModel.chooseGender("male")
        }
        binding.female.setOnClickListener {
            binding.female.setBackgroundResource(R.drawable.selected_button_bg)
            binding.male.background = null
            viewModel.chooseGender("female")
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.accountCreate.setOnClickListener {
            findNavController().run {
                popBackStack()
                navigate(R.id.nav_account_create_success)
            }
        }
    }

    private fun listeners() {
        viewModel.validForm.observe(viewLifecycleOwner){
            if(it == true){
                binding.accountCreateLay.setBackgroundResource(R.drawable.create_account_gradient)
                binding.accountCreate.isEnabled = true
                binding.accountCreateText.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
            }else{
                binding.accountCreateLay.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_grey_stroke_dark))
                binding.accountCreate.isEnabled = false
                binding.accountCreateText.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorDefaultBgColor))
            }
        }
    }


}