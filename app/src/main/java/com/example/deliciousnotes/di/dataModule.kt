package com.example.deliciousnotes.di


import com.example.deliciousnotes.ui.calculator.CalculatorViewModel
import com.example.deliciousnotes.ui.list.ListRecipesViewModel
import com.example.deliciousnotes.ui.newRecipe.NewRecipeViewModel
import com.example.deliciousnotes.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    viewModel { CalculatorViewModel() }
    viewModel { SettingsViewModel(get()) }
    viewModel { ListRecipesViewModel(get()) }
    viewModel { NewRecipeViewModel(get()) }
}