package com.example.deliciousnotes.domain.recipesList.api

import com.example.deliciousnotes.domain.recipesList.model.Recipe

interface ManageRecipeRepository {
     suspend fun insertRecipe(recipe: Recipe)
     suspend fun getAllRecipes(): List<Recipe>
     suspend fun clearAllRecipes()
}