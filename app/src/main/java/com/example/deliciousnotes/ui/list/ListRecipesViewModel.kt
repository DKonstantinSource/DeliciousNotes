package com.example.deliciousnotes.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliciousnotes.domain.recipesList.api.ManageRecipeRepository
import com.example.deliciousnotes.domain.recipesList.model.Recipe
import kotlinx.coroutines.launch

class ListRecipesViewModel(private val repository: ManageRecipeRepository) : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes


    init {
        Log.d("ListRecipesViewModel", "Repository: $repository")
        fetchAllRecipes()
    }

    fun fetchAllRecipes() {
        viewModelScope.launch {
            val allRecipes = repository.getAllRecipes()
            _recipes.value = allRecipes
        }
    }

    fun clearAllRecipes() {
        viewModelScope.launch {
            repository.clearAllRecipes()
            fetchAllRecipes()
        }
    }
}