package com.example.deliciousnotes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deliciousnotes.data.model.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRecipes(recipes: List<Recipe>)

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>

    @Query("DELETE FROM recipes")
    suspend fun clearAll()
}