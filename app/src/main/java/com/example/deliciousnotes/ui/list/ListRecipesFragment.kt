package com.example.deliciousnotes.ui.list

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliciousnotes.databinding.FragmentListRecipesBinding
import com.example.deliciousnotes.domain.recipesList.model.Recipe
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListRecipesFragment : Fragment() {

    private var _binding: FragmentListRecipesBinding? = null
    private lateinit var recipeAdapter: RecipeAdapter
    private val listRecipesViewModel: ListRecipesViewModel by viewModel()
    private var recipeList: List<Recipe> = listOf()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListRecipesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()

        try {
            listRecipesViewModel.fetchAllRecipes()
        } catch (e: Exception) {
            Log.e("ViewModelError", "Error calling fetchAllRecipes", e)
        }

        listRecipesViewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            Log.d("ListRecipesFragment", "Recipes size: ${recipes.size}")
            recipeAdapter.updateRecipes(recipes)
        }

        return root
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun setupRecyclerView() {
        val displayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            requireActivity().getSystemService(Context.WINDOW_SERVICE)
        } else {
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        }

        val width = displayMetrics.widthPixels

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recipeAdapter = RecipeAdapter(requireContext(), recipeList)
        binding.recyclerView.adapter = recipeAdapter

        setItemWidth(width)
    }

    private fun setItemWidth(screenWidth: Int) {
        recipeAdapter.onItemWidthSet(screenWidth / 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}