package com.example.deliciousnotes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.deliciousnotes.data.model.Recipe

@Dao
interface RecipeDao {
    @Insert
     fun insert(recipe: Recipe)

    @Query("SELECT * FROM recipes")
     fun getAllRecipes(): List<Recipe>

    @Query("DELETE FROM recipes")
     fun clearAll()
}