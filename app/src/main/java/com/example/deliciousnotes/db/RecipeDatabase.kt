package com.example.deliciousnotes.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deliciousnotes.data.model.Recipe
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                )
                    .addCallback(RecipeDatabaseCallback())
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class RecipeDatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d("DatabaseCallback", "Database created successfully.")
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        insertInitialRecipes(database.recipeDao())
                    }
                }
            }

            private suspend fun insertInitialRecipes(recipeDao: RecipeDao) {
                val initialRecipes = listOf(
                    Recipe(
                        name = "Овсянка с фруктами",
                        image = "test_image",
                        mealTime = "Завтрак",
                        cookingTechnology = "Варить 10 минут",
                        compound = "Овсянка, фрукты, мед"
                    ),
                    Recipe(
                        name = "Куриный суп",
                        image = "test_image",
                        mealTime = "Обед",
                        cookingTechnology = "Варить 40 минут",
                        compound = "Курица, картофель, морковь"
                    ),
                    Recipe(
                        name = "Паста с овощами",
                        image = "test_image",
                        mealTime = "Обед",
                        cookingTechnology = "Варить 15 минут",
                        compound = "Макароны, помидоры, базилик"
                    ),
                    Recipe(
                        name = "Салат Цезарь",
                        image = "test_image",
                        mealTime = "Ужин",
                        cookingTechnology = "Смешать ингредиенты",
                        compound = "Курица, салат ромэн, соус цезарь"
                    )
                )
                try {
                    recipeDao.insertAllRecipes(initialRecipes)
                    Log.d("DatabaseCache", "Inserted recipes: ${initialRecipes.size}")
                } catch (e: Exception) {
                    Log.e("DatabaseCache", "Error inserting recipes", e)
                }
            }
        }
    }
}
