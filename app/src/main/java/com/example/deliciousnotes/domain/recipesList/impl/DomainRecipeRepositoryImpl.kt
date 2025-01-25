package com.example.deliciousnotes.domain.recipesList.impl

import android.util.Log
import com.example.deliciousnotes.domain.recipesList.api.ManageRecipeRepository
import com.example.deliciousnotes.domain.recipesList.repository.RecipeRepository
import com.example.deliciousnotes.domain.recipesList.model.Recipe as DomainRecipe
import com.example.deliciousnotes.mapper.RecipeMapper


class DomainRecipeRepositoryImpl(
    private val recipeRepository: RecipeRepository
) : ManageRecipeRepository {

    override fun insertRecipe(recipe: DomainRecipe) {
        recipeRepository.insertRecipe(RecipeMapper.map(recipe))
    }

    override fun getAllRecipes(): List<DomainRecipe> {
        Log.d("DomainRecipeRepository", "Fetching all recipes.")
        return try {
            recipeRepository.getAllRecipes().map { RecipeMapper.map(it) }
        } catch (e: Exception) {
            Log.e("DomainRecipeRepository", "Error fetching recipes", e)
            emptyList()
        }
    }

    override fun clearAllRecipes() {
        recipeRepository.clearAllRecipes()
    }
}