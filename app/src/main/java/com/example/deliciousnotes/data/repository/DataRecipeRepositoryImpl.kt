package com.example.deliciousnotes.data.repository

import com.example.deliciousnotes.data.model.Recipe
import com.example.deliciousnotes.db.RecipeDao
import com.example.deliciousnotes.domain.recipesList.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRecipeRepositoryImpl(
    private val recipeDao: RecipeDao
) : RecipeRepository {

    override suspend fun insertRecipe(recipe: Recipe) {
        withContext(Dispatchers.IO) {
            recipeDao.insertAllRecipes(listOf(recipe))
        }
    }

    override suspend fun getAllRecipes(): List<Recipe> {
        return withContext(Dispatchers.IO) {
            recipeDao.getAllRecipes()
        }
    }

    override suspend fun clearAllRecipes() {
        withContext(Dispatchers.IO) {
            recipeDao.clearAll()
        }
    }
}
