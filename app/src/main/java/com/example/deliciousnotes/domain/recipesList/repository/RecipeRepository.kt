package com.example.deliciousnotes.domain.recipesList.repository

import com.example.deliciousnotes.data.model.Recipe

interface RecipeRepository {
    fun insertRecipe(recipe: Recipe)
    fun getAllRecipes(): List<Recipe>
    fun clearAllRecipes()
}