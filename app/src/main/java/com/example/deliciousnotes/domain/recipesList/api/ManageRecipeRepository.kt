package com.example.deliciousnotes.domain.recipesList.api

import com.example.deliciousnotes.domain.recipesList.model.Recipe

interface ManageRecipeRepository {
     fun insertRecipe(recipe: Recipe)
     fun getAllRecipes(): List<Recipe>
     fun clearAllRecipes()
}