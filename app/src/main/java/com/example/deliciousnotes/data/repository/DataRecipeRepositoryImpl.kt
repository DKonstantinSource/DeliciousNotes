package com.example.deliciousnotes.data.repository

import com.example.deliciousnotes.data.model.Recipe
import com.example.deliciousnotes.db.RecipeDao
import com.example.deliciousnotes.domain.recipesList.repository.RecipeRepository

class DataRecipeRepositoryImpl(
    private val recipeDao: RecipeDao
) : RecipeRepository {

    override fun insertRecipe(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    override fun getAllRecipes(): List<Recipe> {
        return recipeDao.getAllRecipes()
    }

    override fun clearAllRecipes() {
        recipeDao.clearAll()
    }
}