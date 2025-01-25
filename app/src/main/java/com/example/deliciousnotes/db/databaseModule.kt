package com.example.deliciousnotes.db

import com.example.deliciousnotes.data.repository.DataRecipeRepositoryImpl
import com.example.deliciousnotes.domain.recipesList.api.ManageRecipeRepository
import com.example.deliciousnotes.domain.recipesList.impl.DomainRecipeRepositoryImpl
import com.example.deliciousnotes.domain.recipesList.repository.RecipeRepository
import org.koin.dsl.module

val databaseModule = module {
    single { RecipeDatabase.getDatabase(get()).recipeDao() }
    single<RecipeRepository> { DataRecipeRepositoryImpl(get()) }
    single<ManageRecipeRepository> { DomainRecipeRepositoryImpl(get<RecipeRepository>()) }
}