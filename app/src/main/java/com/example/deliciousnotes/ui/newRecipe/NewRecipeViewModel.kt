package com.example.deliciousnotes.ui.newRecipe


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliciousnotes.domain.recipesList.api.ManageRecipeRepository
import com.example.deliciousnotes.domain.recipesList.model.Recipe
import kotlinx.coroutines.launch

class NewRecipeViewModel(private val repository: ManageRecipeRepository) : ViewModel() {

    fun saveRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }
}