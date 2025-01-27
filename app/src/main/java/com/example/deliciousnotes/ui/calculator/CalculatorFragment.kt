package com.example.deliciousnotes.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deliciousnotes.databinding.FragmentCalculatorBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorFragment : Fragment() {

    private val calculatorViewModel: CalculatorViewModel by viewModel()
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val root: View = binding.root


        calculatorViewModel.result.observe(viewLifecycleOwner) { result ->
            binding.pfcResult.text = result
        }

        binding.calculateButton.setOnClickListener {
            val proteins = binding.proteinsEditText.text.toString().toIntOrNull() ?: 0
            val fats = binding.fatFitEditText.text.toString().toIntOrNull() ?: 0
            val carbohydrates = binding.carbohydratesEditText.text.toString().toIntOrNull() ?: 0

            calculatorViewModel.resultCount(proteins, fats, carbohydrates)
            if (proteins <= 0 && fats <= 0 && carbohydrates <= 0) {
                binding.pfcResult.visibility = View.GONE
            } else {
                binding.pfcResult.visibility = View.VISIBLE
            }
        }

        binding.clearCalculateButton.setOnClickListener {
            binding.proteinsEditText.text.clear()
            binding.fatFitEditText.text.clear()
            binding.carbohydratesEditText.text.clear()

            calculatorViewModel.resultCount(0, 0, 0)
            binding.pfcResult.visibility = View.GONE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}