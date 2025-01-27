package com.example.deliciousnotes.domain.recipesList.repository

import com.example.deliciousnotes.data.model.Recipe

interface RecipeRepository {
    suspend fun insertRecipe(recipe: Recipe)
    suspend fun getAllRecipes(): List<Recipe>
    suspend fun clearAllRecipes()
}