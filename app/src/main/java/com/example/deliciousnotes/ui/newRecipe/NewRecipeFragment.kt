package com.example.deliciousnotes.ui.newRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.deliciousnotes.R
import com.example.deliciousnotes.databinding.FragmentNewRecipeBinding
import com.example.deliciousnotes.domain.recipesList.model.Recipe
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewRecipeFragment : Fragment() {

    private var _binding: FragmentNewRecipeBinding? = null
    private val binding get() = _binding!!

    private val newRecipeViewModel: NewRecipeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewRecipeBinding.inflate(inflater, container, false)
        setupSpinner()
        setupFocusListener()
        binding.saveRecipe.setOnClickListener { saveRecipe() }

        KeyboardVisibilityEvent.setEventListener(requireActivity()) { isOpen ->
            if (isOpen) {
                binding.scrollView.post {
                    binding.scrollView.smoothScrollTo(0, binding.scrollView.bottom)
                }
            }
        }

        return binding.root
    }

    private fun setupSpinner() {
        val mealTimes = arrayOf(
            getString(R.string.breakfast),
            getString(R.string.lunch),
            getString(R.string.dinner)
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, mealTimes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mealTime.adapter = adapter

        binding.mealTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                view?.let {
                    val selectedItem = parent.getItemAtPosition(position)
                    //TODO result on click
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //TODO result on click
            }
        }
    }

    private fun setupFocusListener() {
        //TODO подумать над фокусом, как реализовать лаконичнее
        binding.ingredientInput.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.scrollView.post {
                    binding.scrollView.smoothScrollTo(0, view.top + view.height)
                }
            }
        }

        binding.recipeTitle.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.scrollView.post {
                    binding.scrollView.smoothScrollTo(0, view.top + view.height)
                }
            }
        }

        binding.technologyCooking.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.scrollView.post {
                    binding.scrollView.smoothScrollTo(0, view.top + view.height)
                }
            }
        }
    }

    private fun saveRecipe() {
        val title = binding.recipeTitle.text.toString().trim()
        val ingredients = binding.ingredientInput.text.toString().trim()
        val mealTime = binding.mealTime.selectedItem.toString()
        val cookingTechnology = binding.technologyCooking.text.toString().trim()
        val compound = binding.ingredientInput.text.toString().trim()

        if (title.isNotEmpty() && ingredients.isNotEmpty()) {
            val recipe = Recipe(title, ingredients, mealTime, cookingTechnology, compound)
            newRecipeViewModel.saveRecipe(recipe)

            binding.recipeTitle.text.clear()
            binding.ingredientInput.text.clear()
            binding.mealTime.setSelection(0)
            binding.technologyCooking.text.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}