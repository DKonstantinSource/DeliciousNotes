package com.example.deliciousnotes.ui.list

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliciousnotes.domain.recipesList.api.ManageRecipeRepository
import com.example.deliciousnotes.domain.recipesList.model.Recipe

class ListRecipesViewModel(private val repository: ManageRecipeRepository) : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    private val handler = Handler(Looper.getMainLooper())
    private var debounceRunnable: Runnable? = null

    init {
        Log.d("ListRecipesViewModel", "Repository: $repository")
        fetchAllRecipes()
    }

    fun fetchAllRecipes() {
        debounceRunnable?.let { handler.removeCallbacks(it) }
        debounceRunnable = Runnable {
            val recipesFromRepo = repository.getAllRecipes()
            _recipes.value = recipesFromRepo
        }
        handler.postDelayed(debounceRunnable!!, 300)
    }

    fun clearAllRecipes() {
        repository.clearAllRecipes()
        fetchAllRecipes()
    }
}